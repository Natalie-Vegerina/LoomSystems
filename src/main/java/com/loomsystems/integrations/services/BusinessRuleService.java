package com.loomsystems.integrations.services;

import com.loomsystems.integrations.domain.BusinessRuleCreateRequestModel;
import com.loomsystems.integrations.Config;
import com.loomsystems.integrations.domain.incidents.servicenow.SNResponseModel;
import org.apache.http.auth.AuthenticationException;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Optional;

public class BusinessRuleService {
    private final String hostAddressPattern = "%host_address%";
    private final String businessRuleName = "Natalie incident rule";
    private RequestService requestService = new RequestService();

    public Optional<SNResponseModel> createOrUpdate(String hostAddress) {
        try {
            BusinessRuleCreateRequestModel model = createModel(hostAddress);
            String query = URLEncoder.encode("GOTOname=" + businessRuleName, "UTF-8");
            Optional<SNResponseModel> existingRule = requestService.exists("/api/now/table/sys_script", query, SNResponseModel.class);
            if (existingRule.isPresent()) {
                return requestService.sendPut("/api/now/table/sys_script/" + existingRule.get().getId(), model, SNResponseModel.class);
            }

            return requestService.sendPost("/api/now/table/sys_script", model, SNResponseModel.class);
        } catch (IOException | AuthenticationException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    private BusinessRuleCreateRequestModel createModel(String hostAddress) {
        BusinessRuleCreateRequestModel model = new BusinessRuleCreateRequestModel();
        model.setActionUpdate("true");
        model.setWhen("after");
        model.setOrder("51");
        model.setPriority("100");
        model.setScript(Config.getInstance().getBusinessRulePattern()
                .replace(hostAddressPattern, Config.getInstance().getHostAddress()));
        model.setName(businessRuleName);
        return model;
    }
}
