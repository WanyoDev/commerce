package com.wanyoike.productservice.service;

import com.wanyoike.productservice.dto.*;
import com.wanyoike.productservice.exceptions.BrandNotFoundException;
import com.wanyoike.productservice.exceptions.CategoryNotFoundException;
import com.wanyoike.productservice.exceptions.ProductNotFoundException;
import com.wanyoike.productservice.model.Brand;
import com.wanyoike.productservice.model.Category;
import com.wanyoike.productservice.model.Product;
import com.wanyoike.productservice.repository.BrandRepository;
import com.wanyoike.productservice.repository.CategoryRepository;
import com.wanyoike.productservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;

    private final ProductMapper productMapper;
    private final BrandMapper brandMapper;
    private final CategoryMapper categoryMapper;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, BrandRepository brandRepository,
                              ProductMapper productMapper, BrandMapper brandMapper, CategoryMapper categoryMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.brandRepository = brandRepository;
        this.productMapper = productMapper;
        this.brandMapper = brandMapper;
        this.categoryMapper = categoryMapper;
    }

    //PRODUCT - CREATE, READ, UPDATE, DELETE
    @Override
    public ProductResponseDTO newProduct(ProductRequestDTO productRequestDTO) {

        Product product = productMapper.toProductEntity(productRequestDTO);

        Category category = categoryRepository.findById(productRequestDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Brand brand = brandRepository.findById(productRequestDTO.getBrandId())
                .orElseThrow(() -> new RuntimeException("Brand not found"));

        product.setCategory(category);
        product.setBrand(brand);
        product.setCreatedAt(LocalDateTime.now());

        Product saved = productRepository.save(product);
        return productMapper.toProductResponseDto(saved);
    }

    @Override
    public List<ProductResponseDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return productMapper.toListResponseDto(products);
    }

    @Override
    public void deleteProduct(UUID id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new ProductNotFoundException("Product not found");
        }
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductResponseDTO> getAllProductsByCategoryId(UUID categoryId) {
        //find if the category is present
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found: " + categoryId));

        if (!category.isDeleted()) {
            //should return a list of all products in that category that have not been deleted
            List<Product> products = productRepository.findByCategory(category);
            return productMapper.toListResponseDto(products);
        }
        return null;
    }

    @Override
    public List<ProductResponseDTO> getAllProductsByBrandId(UUID brandId) {
        Brand brand = brandRepository.findById(brandId)
                .orElseThrow(() -> new BrandNotFoundException("Brand not found: " + brandId));

        List<Product> products = productRepository.findByBrand(brand);
        return productMapper.toListResponseDto(products);
    }

    @Override
    public ProductResponseDTO getProductById(UUID id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new ProductNotFoundException("Product not found");
        }
        return productMapper.toProductResponseDto(product.get());
    }

    @Override
    public void reduceStock(UUID productId, Integer quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found: " + productId));

        if (product.getQuantity() < quantity) {
            throw new RuntimeException("Insufficient stock");
        }
        product.setQuantity(product.getQuantity() - quantity);
        productRepository.save(product);

    }

    @Override
    public ProductResponseDTO updateProduct(UUID id, ProductRequestDTO productRequestDTO) {
        Product productExists=productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found: " + id));

        Category category=categoryRepository.findById(productRequestDTO.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException("Category not found: " + productRequestDTO.getCategoryId()));

        Brand brand=brandRepository.findById(productRequestDTO.getBrandId())
                        .orElseThrow(() -> new BrandNotFoundException("Brand not found: " + productRequestDTO.getBrandId()));

        productExists.setProduct(productRequestDTO.getProduct());
        productExists.setDescription(productRequestDTO.getDescription());
        productExists.setPrice(productRequestDTO.getPrice());
        productExists.setQuantity(productRequestDTO.getQuantity());
        productExists.setCategory(category);
        productExists.setBrand(brand);
        productRepository.save(productExists);

        return productMapper.toProductResponseDto(productExists);
    }

    //CATEGORY - CREATE, READ, UPDATE, DELETE
    @Override
    public CategoryDTO newCategory(CategoryRequestDTO requestDTO) {

        Category category = categoryMapper.toCategory(requestDTO);
        category.setCreatedAt(LocalDateTime.now());

        Category saved = categoryRepository.save(category);
        return categoryMapper.toCategoryDTO(saved);
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categoryMapper.toCategoryListDTO(categories);
    }

    @Override
    public void deleteCategory(UUID id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found: " + id));
        category.setDeleted(true);

        List<Brand> brands = brandRepository.findByCategory(category);
        brands.forEach(brand -> brand.setDeleted(true));

        List<Product> products = productRepository.findByCategory(category);
        products.forEach(product -> product.setActive(false));

        categoryMapper.toCategoryDTO(categoryRepository.save(category));
        productMapper.toListResponseDto(productRepository.saveAll(products));
    }

    //BRAND - CREATE, READ, UPDATE, DELETE
    @Override
    public BrandDTO newBrand(BrandRequestDTO requestDTO) {

        Brand brand = brandMapper.toBrand(requestDTO);
        brand.setCreatedAt(LocalDateTime.now());

        Brand saved = brandRepository.save(brand);
        return brandMapper.toBrandDTO(saved);
    }

    @Override
    public List<BrandDTO> getAllBrands() {
        List<Brand> brands = brandRepository.findAll();
        return brandMapper.toBrandListDTO(brands);
    }

    @Override
    public void deleteBrand(UUID id) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new BrandNotFoundException("Brand not found: " + id));
        brand.setDeleted(true);

        List<Product> products = productRepository.findByBrand(brand);
        products.forEach(product -> product.setActive(false));

        brandMapper.toBrandDTO(brandRepository.save(brand));
        productMapper.toListResponseDto(productRepository.saveAll(products));
    }

}
