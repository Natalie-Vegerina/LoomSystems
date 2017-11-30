package com.loomsystems.integrations.domain.incidents.servicenow;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SNIncidentModel {
    private String number;
    @JsonProperty("short_description")
    private String shortDescription;
    private String priority;
    private String severity;
    private String category;
    private String state;
    private String impact;
    private String active;
    @JsonProperty("opened_at")
    private String openedAt;
    private String notify;
    @JsonProperty("incident_state")
    private String incidentState;
    private String urgency;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getImpact() {
        return impact;
    }

    public void setImpact(String impact) {
        this.impact = impact;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getNotify() {
        return notify;
    }

    public void setNotify(String notify) {
        this.notify = notify;
    }

    public String getUrgency() {
        return urgency;
    }

    public void setUrgency(String urgency) {
        this.urgency = urgency;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getOpenedAt() {
        return openedAt;
    }

    public void setOpenedAt(String openedAt) {
        this.openedAt = openedAt;
    }

    public String getIncidentState() {
        return incidentState;
    }

    public void setIncidentState(String incidentState) {
        this.incidentState = incidentState;
    }

}
