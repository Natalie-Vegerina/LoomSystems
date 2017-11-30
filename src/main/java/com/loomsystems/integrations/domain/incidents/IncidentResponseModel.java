package com.loomsystems.integrations.domain.incidents;

public class IncidentResponseModel extends IncidentCreateResponseModel {
    private String closedAt;
    private String closedBy;

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
