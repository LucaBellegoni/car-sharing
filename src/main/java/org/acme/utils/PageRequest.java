package org.acme.utils;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.QueryParam;
import lombok.Data;

@Data
public class PageRequest {

    @PositiveOrZero
    @QueryParam("pageIndex")
    private int pageIndex;

    @Positive
    @DefaultValue("20")
    @QueryParam("pageSize")
    private int pageSize;

}
