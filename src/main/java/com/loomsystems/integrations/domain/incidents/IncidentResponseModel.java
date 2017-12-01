package com.loomsystems.integrations.domain.incidents;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IncidentResponseModel extends IncidentCreateResponseModel {
    private String closedAt;
    private String closedBy;
}
