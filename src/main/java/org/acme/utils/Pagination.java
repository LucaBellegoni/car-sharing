package org.acme.utils;

import lombok.Data;

@Data
public class Pagination {

    private int pageIndex;
    private int pageSize;
    private int total;

}
