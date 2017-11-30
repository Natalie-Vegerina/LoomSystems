package com.loomsystems.integrations.services;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loomsystems.integrations.Config;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Iterator;
import java.util.Optional;

public class RequestService {
    public <TRequest, TResponse> Optional<TResponse> sendPost(String resourcePath, TRequest model, Class<TResponse> responseClass) throws IOException, AuthenticationException {
        return sendMethod("POST", 201, resourcePath, model, responseClass);
    }

    public <TRequest, TResponse> Optional<TResponse> sendPut(String resourcePath, TRequest model, Class<TResponse> responseClass) throws IOException, AuthenticationException {
        return sendMethod("PUT", 200, resourcePath, model, responseClass);
    }

    public <TResponse> Optional<TResponse> exists(String resourcePath, String query, Class<TResponse> responseClass) throws AuthenticationException, IOException {
        CloseableHttpClient client = null;
        try {
            client = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(Config.getInstance().getServiceNowUrl() + resourcePath + "?sysparm_query=" + query);


            UsernamePasswordCredentials creds = new UsernamePasswordCredentials(
                    Config.getInstance().getServiceNowUser(), Config.getInstance().getServiceNowPassword());
            httpGet.addHeader(new BasicScheme().authenticate(creds, httpGet, null));
            httpGet.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");

            CloseableHttpResponse httpResponse = client.execute(httpGet);

            HttpEntity responseEntity = httpResponse.getEntity();
            if (responseEntity != null) {
                String json = EntityUtils.toString(responseEntity, "UTF-8");
                if(httpResponse.getStatusLine().getStatusCode() != 200) {
                    System.out.println("ServiceNow error: " + json);
                    return Optional.empty();
                }

                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
                Iterator<JsonNode> elements = objectMapper.readTree(json).get("result").elements();
                if(elements.hasNext()) {
                    JsonNode existingItem = elements.next();
                    TResponse response = objectMapper.readValue(existingItem.toString(), responseClass);
                    return Optional.of(response);
                }
            }

            return Optional.empty();
        }
        finally {
            if(client != null) {
                client.close();
            }
        }
    }

    private <TRequest, TResponse> Optional<TResponse> sendMethod(String methodName, int responseCode, String resourcePath, TRequest model, Class<TResponse> responseClass) throws IOException, AuthenticationException {
        CloseableHttpClient client = null;
        try {
            client = HttpClients.createDefault();
            HttpEntityEnclosingRequestBase httpRequest = createHttpRequest(methodName, resourcePath);

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            String content = objectMapper.writeValueAsString(model);

            httpRequest.setEntity(new StringEntity(content));

            UsernamePasswordCredentials creds = new UsernamePasswordCredentials(
                    Config.getInstance().getServiceNowUser(), Config.getInstance().getServiceNowPassword());
            httpRequest.addHeader(new BasicScheme().authenticate(creds, httpRequest, null));
            httpRequest.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");

            CloseableHttpResponse httpResponse = client.execute(httpRequest);

            HttpEntity responseEntity = httpResponse.getEntity();
            if (responseEntity != null) {
                String json = EntityUtils.toString(responseEntity, "UTF-8");
                if(httpResponse.getStatusLine().getStatusCode() != responseCode) {
                    System.out.println("ServiceNow error: " + json);
                    return Optional.empty();
                }

                TResponse response = objectMapper.readValue(objectMapper.readTree(json).get("result").toString(), responseClass);
                return Optional.of(response);
            }

            return Optional.empty();
        }
        finally {
            if(client != null) {
                client.close();
            }
        }
    }

    private HttpEntityEnclosingRequestBase createHttpRequest(String methodName, String resourcePath) {
        switch (methodName) {
            case "PUT" :
                return new HttpPut(Config.getInstance().getServiceNowUrl() + resourcePath);
            default:
                return new HttpPost(Config.getInstance().getServiceNowUrl() + resourcePath);
        }
    }

}
