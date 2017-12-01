package com.loomsystems.integrations.domain.incidents;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IncidentCreateRequestModel {
    private String number;
    private String shortDescription;
    private String priority;
    private String severity;
    private String category;
    private String state;
    private String impact;
    private String active;
    private String openedAt;
    private String notify;
    private String incidentState;
    private String urgency;
    private String assignedTo;
    private String assignmentGroup;
    private String openedBy;
    private String callerId;
}
