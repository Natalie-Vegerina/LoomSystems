package com.loomsystems.integrations.domain.incidents;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Incident {
    private String number;
    private String shortDescription;
    private String priority;
    private String severity;
    private String id;
    private String category;
    private String updatedOn;
    private String openedBy;
    private String closedBy;
    private String state;
    private String impact;
    private String active;
    private String openedAt;
    private String closedAt;
    private String callerId;
    private String notify;
    private String incidentState;
    private String urgency;
    private String assignedTo;
    private String assignmentGroup;
}
