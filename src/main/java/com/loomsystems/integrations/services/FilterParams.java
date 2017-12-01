package com.loomsystems.integrations.services;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FilterParams {
    private String query;
    private String order;
    private String orderDirection;
    private Integer offset;
    private Integer limit;
}
