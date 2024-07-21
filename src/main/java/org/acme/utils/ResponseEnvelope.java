package org.acme.utils;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseEnvelope<T> {

    @JsonInclude(Include.NON_DEFAULT)
    private T data;

    @JsonInclude(Include.NON_DEFAULT)
    private List<String> errors;

    @JsonInclude(Include.NON_DEFAULT)
    private Pagination pagination;

}
