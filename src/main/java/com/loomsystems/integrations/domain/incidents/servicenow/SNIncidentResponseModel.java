package com.loomsystems.integrations.domain.incidents.servicenow;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SNIncidentResponseModel extends SNIncidentModel {

    @JsonProperty("sys_id")
    private String id;
    @JsonProperty("updated_on")
    private String updatedOn;
    @JsonProperty("opened_by")
    private LinkObject openedBy;
    @JsonProperty("caller_id")
    private LinkObject callerId;
    @JsonProperty("assigned_to")
    private LinkObject assignedTo;

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

    public LinkObject getOpenedBy() {
        return openedBy;
    }

    public void setOpenedBy(LinkObject openedBy) {
        this.openedBy = openedBy;
    }

    public LinkObject getCallerId() {
        return callerId;
    }

    public void setCallerId(LinkObject callerId) {
        this.callerId = callerId;
    }

    public LinkObject getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(LinkObject assignedTo) {
        this.assignedTo = assignedTo;
    }
}
