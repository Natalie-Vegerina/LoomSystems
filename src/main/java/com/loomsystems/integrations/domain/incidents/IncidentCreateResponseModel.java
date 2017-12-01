package com.loomsystems.integrations.domain.incidents;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IncidentCreateResponseModel extends IncidentUpdateRequestModel {
    private String id;
    private String updatedOn;
}
