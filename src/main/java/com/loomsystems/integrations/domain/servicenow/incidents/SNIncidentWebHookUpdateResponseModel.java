package com.loomsystems.integrations.domain.servicenow.incidents;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SNIncidentWebHookUpdateResponseModel extends SNIncidentRequestModel {
    @JsonProperty("sys_id")
    private String id;
    @JsonProperty("updated_on")
    private String updatedOn;
    @JsonProperty("closed_at")
    private String closedAt;
    @JsonProperty("closed_by")
    private String closedBy;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(String updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getClosedAt() {
        return closedAt;
    }

    public void setClosedAt(String closedAt) {
        this.closedAt = closedAt;
    }

    public String getClosedBy() {
        return closedBy;
    }

    public void setClosedBy(String closedBy) {
        this.closedBy = closedBy;
    }
}
