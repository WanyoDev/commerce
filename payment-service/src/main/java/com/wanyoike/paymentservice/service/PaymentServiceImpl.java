package com.wanyoike.paymentservice.service;

import com.wanyoike.paymentservice.dto.PaymentMapper;
import com.wanyoike.paymentservice.dto.PaymentResponseDto;
import com.wanyoike.paymentservice.exceptions.PaymentExistsException;
import com.wanyoike.paymentservice.exceptions.PaymentNotFoundException;
import com.wanyoike.paymentservice.kafka.OrderEvent;
import com.wanyoike.paymentservice.model.Payment;
import com.wanyoike.paymentservice.model.PaymentStatus;
import com.wanyoike.paymentservice.repository.PaymentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    private final PaymentMapper paymentMapper;

    @Override
    public void processPayment(OrderEvent orderEvent) {

        if (paymentRepository.existsByOrderId(orderEvent.orderId())) {
            throw new PaymentExistsException("Payment already exists for order" + orderEvent.orderId());
        }

        Payment payment = Payment.builder()
                .orderId(orderEvent.orderId())
                .userId(orderEvent.userId())
                .amount(orderEvent.amount())
                .paymentStatus(PaymentStatus.PROCESSING)
                .paymentMethod(orderEvent.paymentMethod())
                .paymentReference(generatePaymentReference())
                .build();

        paymentRepository.save(payment);

        //You can implement the PaymentResult - Succeeded or Failed
    }

    @Override
    @Transactional
    public PaymentResponseDto getPayment(UUID paymentId) {

        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new PaymentNotFoundException("Payment does not exist for id" + paymentId));
        return paymentMapper.toDto(payment);
    }

    @Override
    public List<PaymentResponseDto> getPayments() {
        return paymentMapper.toDto(paymentRepository.findAll());
    }

    @Override
    public List<PaymentResponseDto> getPaymentsByUserId(UUID userId) {
       return paymentRepository.findByUserId(userId)
               .stream()
               .map((payment)-> paymentMapper.toDto(payment))
               .toList();
    }

    public String generatePaymentReference() {
        return "PAY - " + UUID.randomUUID().toString()
                .substring(0, 8)
                .toUpperCase();
    }
}
