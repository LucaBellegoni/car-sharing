package org.acme.utils;

import java.util.List;

import lombok.Data;

@Data
public class ValidationResult {

    private boolean isValid;
    private String field;
    private List<String> errors;

}
