package com.loomsystems.integrations.domain.incidents.servicenow;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SNResponseModel {
    @JsonProperty("sys_id")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String sys_id) {
        this.id = sys_id;
    }
}
