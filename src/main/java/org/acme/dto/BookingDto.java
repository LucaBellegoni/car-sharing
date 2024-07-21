package org.acme.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BookingDto {

    private Long id;

    @NotBlank
    private String field1;

    @NotBlank
    private String field2;

}
