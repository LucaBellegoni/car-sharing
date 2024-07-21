package org.acme.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class VehicleDto {

    private Long id;

    @NotBlank
    private String type;

    @NotBlank
    private String manufacturer;

    @NotBlank
    private String model;

    @NotNull
    @Positive
    private int registrationYear;

    @NotNull
    @PositiveOrZero
    private double price;

    private String imageUrl;

}
