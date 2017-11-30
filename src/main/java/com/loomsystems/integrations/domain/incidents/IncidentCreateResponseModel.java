package com.loomsystems.integrations.domain.incidents;

public class IncidentCreateResponseModel extends IncidentUpdateRequestModel {
    private String id;
    private String updatedOn;

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

}
