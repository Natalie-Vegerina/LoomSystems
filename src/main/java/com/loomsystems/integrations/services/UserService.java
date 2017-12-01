package com.loomsystems.integrations.services;

import com.loomsystems.integrations.domain.servicenow.users.SNUserResponseModel;
import com.loomsystems.integrations.domain.users.UserResponseModel;
import org.apache.http.auth.AuthenticationException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class UserService {
    private RequestService requestService = new RequestService();
    private ConvertService convertService = new ConvertService();

    public List<UserResponseModel> list(FilterParams filter) {
        try {
            return requestService.list("/api/now/table/sys_user", filter, SNUserResponseModel.class)
                    .stream()
                    .map(convertService::convert)
                    .collect(toList());
        } catch (IOException | AuthenticationException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
