package com.wanyoike.orderservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotBlank(message = "Enter valid phone number")
    @Column(unique = true, nullable = false)
    private String phoneNumber;

    //Should retrieve email of user logged in
    @Column(name = "email", insertable = false, updatable = false)
    @Email
    private String email;

    @Column(name = "country", nullable = false)
    @NotBlank(message = "Enter country of residence")
    private String country;

    @Column(name = "city", nullable=false)
    @NotBlank(message = "Enter current city you live in")
    private String city;

    @Column(name = "postal_code", nullable = false)
    @NotBlank(message = "Enter current postal code")
    private String postalCode;

    @Column(name = "street_address", nullable = false)
    @NotBlank(message = "Enter street address")
    private String streetAddress;
}
