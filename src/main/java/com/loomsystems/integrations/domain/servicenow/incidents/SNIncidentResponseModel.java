package com.loomsystems.integrations.domain.servicenow.incidents;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.loomsystems.integrations.domain.servicenow.LinkObject;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
    @JsonProperty("assignment_group")
    private LinkObject assignmentGroup;
}
