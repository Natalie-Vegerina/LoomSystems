package com.loomsystems.integrations.domain.incidents;

public class IncidentUpdateRequestModel extends IncidentCreateRequestModel {
    private String openedBy;
    private String callerId;

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
}
