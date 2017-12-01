package com.loomsystems.integrations.domain.servicenow.incidents;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
}
