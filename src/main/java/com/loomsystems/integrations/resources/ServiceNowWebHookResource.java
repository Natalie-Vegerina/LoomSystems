package com.loomsystems.integrations.resources;


import com.loomsystems.integrations.domain.servicenow.incidents.SNIncidentWebHookUpdateResponseModel;
import com.loomsystems.integrations.services.IncidentService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;

@Path("/api/v1/web-hooks/incidents")
public class ServiceNowWebHookResource {

    @Context
    Request request;

    private IncidentService incidentService = new IncidentService();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateIncident(SNIncidentWebHookUpdateResponseModel body) {
        incidentService.updateEvent(body);
        System.out.println("Recieved incident update with id " + body.getId());
    }
}
