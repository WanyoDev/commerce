package com.wanyoike.paymentservice.dto;

import com.wanyoike.paymentservice.model.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class);

    PaymentResponseDto toDto(Payment payment);

    List<PaymentResponseDto> toDto(List<Payment> payments);

    //No mapping request toEntity, since payment entity isn't created from a REST request
}
