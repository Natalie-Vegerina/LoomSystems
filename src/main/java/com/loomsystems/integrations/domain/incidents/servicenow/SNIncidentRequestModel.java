package com.loomsystems.integrations.domain.incidents.servicenow;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SNIncidentRequestModel extends SNIncidentModel {
    @JsonProperty("opened_by")
    private String openedBy;
    @JsonProperty("caller_id")
    private String callerId;
    @JsonProperty("assigned_to")
    private String assignedTo;

    public String getOpenedBy() {
        return openedBy;
    }

    public void setOpenedBy(String openedBy) {
        this.openedBy = openedBy;
    }

    public String getCallerId() {
        return callerId;
    }

    public void setCallerId(String callerId) {
        this.callerId = callerId;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }
}
