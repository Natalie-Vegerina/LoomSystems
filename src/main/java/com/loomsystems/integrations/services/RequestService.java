package com.loomsystems.integrations.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
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
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
            addAuth(httpGet);

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

    public  <TResponse> List<TResponse> list(String resourcePath, FilterParams filter, Class<TResponse> responseClass) throws IOException, AuthenticationException {
        CloseableHttpClient client = null;
        try {
            client = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(buildUrl(resourcePath, filter));
            addAuth(httpGet);

            CloseableHttpResponse httpResponse = client.execute(httpGet);

            HttpEntity responseEntity = httpResponse.getEntity();
            if (responseEntity != null) {
                String json = EntityUtils.toString(responseEntity, "UTF-8");
                if(httpResponse.getStatusLine().getStatusCode() != 200) {
                    System.out.println("ServiceNow error: " + json);
                    return new ArrayList<>();
                }

                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
                JsonNode result = objectMapper.readTree(json).get("result");
                if(result.iterator().hasNext()) {
                    JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, responseClass);
                    return objectMapper.readValue(result.toString(), javaType);
                }
            }

            return new ArrayList<>();
        }
        finally {
            if(client != null) {
                client.close();
            }
        }
    }

    private void addAuth(HttpRequestBase httpGet) throws AuthenticationException {
        UsernamePasswordCredentials creds = new UsernamePasswordCredentials(
                Config.getInstance().getServiceNowUser(), Config.getInstance().getServiceNowPassword());
        httpGet.addHeader(new BasicScheme().authenticate(creds, httpGet, null));
        httpGet.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");
    }

    private String buildUrl(String resourcePath, FilterParams filter) throws UnsupportedEncodingException {
        Integer limit = filter.getLimit() > 0 ? filter.getLimit() : 10;
        Integer offset = filter.getOffset() > 0 ? filter.getOffset() : 0;
        StringBuilder uriBuilder = new StringBuilder(Config.getInstance().getServiceNowUrl())
                .append(resourcePath)
                .append("?sysparm_offset=").append(offset)
                .append("&sysparm_limit=").append(limit);
        boolean hasQuery = filter.getQuery() != null && !filter.getQuery().isEmpty();
        boolean hasOrder = filter.getOrder() != null && !filter.getOrder().isEmpty();
        if(hasQuery || hasOrder) {
            uriBuilder = uriBuilder.append("&sysparm_query=");
            StringBuilder queryBuilder = new StringBuilder();
            if (hasQuery) {
                queryBuilder = queryBuilder.append(filter.getQuery());
            }

            if(hasQuery && hasOrder) {
                queryBuilder = queryBuilder.append("^");
            }

            if (hasOrder) {
                String order = "ORDERBY";
                if ("desc".equals(filter.getOrderDirection())) {
                    order += "DESC";
                }

                queryBuilder = queryBuilder.append(order).append(filter.getOrder());
            }

            uriBuilder = uriBuilder.append(URLEncoder.encode(queryBuilder.toString(), "UTF-8"));
        }

        return uriBuilder.toString();
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

            addAuth(httpRequest);

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
