package com.loomsystems.integrations.domain.incidents;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IncidentUpdateResponseModel extends IncidentCreateResponseModel {
    private String closedAt;
    private String closedBy;
}
