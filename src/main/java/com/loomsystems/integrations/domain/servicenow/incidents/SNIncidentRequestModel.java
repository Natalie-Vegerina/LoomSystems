package com.loomsystems.integrations.domain.servicenow.incidents;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.loomsystems.integrations.domain.servicenow.LinkObject;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SNIncidentRequestModel extends SNIncidentModel {
    @JsonProperty("opened_by")
    private String openedBy;
    @JsonProperty("caller_id")
    private String callerId;
    @JsonProperty("assigned_to")
    private String assignedTo;
    @JsonProperty("assignment_group")
    private String assignmentGroup;
}
