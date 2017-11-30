package com.loomsystems.integrations.resources;


import com.loomsystems.integrations.Config;
import com.loomsystems.integrations.domain.incidents.IncidentCreateRequestModel;
import com.loomsystems.integrations.domain.incidents.IncidentCreateResponseModel;
import com.loomsystems.integrations.domain.incidents.IncidentResponseModel;
import com.loomsystems.integrations.domain.incidents.servicenow.SNResponseModel;
import com.loomsystems.integrations.services.BusinessRuleService;
import com.loomsystems.integrations.services.IncidentService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import java.util.List;

@Path("/api/v1/businessRules")
public class BusinessRuleResource {

    @Context
    Request request;

    private BusinessRuleService businessRuleService = new BusinessRuleService();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public SNResponseModel createOrUpdate() {
        return businessRuleService.createOrUpdate(Config.getInstance().getHostAddress()).orElse(null);
    }
}
