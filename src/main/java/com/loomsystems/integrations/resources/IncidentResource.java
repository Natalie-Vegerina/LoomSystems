package com.loomsystems.integrations.resources;


import com.loomsystems.integrations.domain.incidents.*;
import com.loomsystems.integrations.services.IncidentService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import java.util.List;

@Path("/api/v1/incidents")
public class IncidentResource {

    @Context
    Request request;

    private IncidentService incidentService = new IncidentService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<IncidentResponseModel> list() {
        return incidentService.list();
    }

    @Path("/closed")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<IncidentResponseModel> listClosed() {
        return incidentService.listClosed();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public IncidentCreateResponseModel create(IncidentCreateRequestModel body) {
        return incidentService.create(body).orElse(null);
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public IncidentUpdateResponseModel update(@PathParam("id") String id, IncidentUpdateRequestModel body) {
        return incidentService.update(id, body).orElse(null);
    }
}
