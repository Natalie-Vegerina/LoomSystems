package com.loomsystems.integrations.resources;

import com.loomsystems.integrations.domain.users.UserResponseModel;
import com.loomsystems.integrations.services.FilterParams;
import com.loomsystems.integrations.services.UserService;

import javax.annotation.Resource;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import java.util.List;

@Path("/api/v1/users")
public class UserResource {

    @Context
    Request request;

    private UserService userService = new UserService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserResponseModel> list(@QueryParam("filter") String filter,
                                        @QueryParam("order") String order,
                                        @QueryParam("orderDirection") @DefaultValue("asc") String orderDirection,
                                        @QueryParam("offset") @DefaultValue("0") Integer offset,
                                        @QueryParam("limit") @DefaultValue("10") Integer limit) {
        FilterParams filterParams = FilterParams.builder()
                .query(filter)
                .order(order)
                .orderDirection(orderDirection)
                .offset(offset)
                .limit(limit)
                .build();
        return userService.list(filterParams);
    }
}
