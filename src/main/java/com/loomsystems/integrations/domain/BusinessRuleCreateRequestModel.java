package com.loomsystems.integrations.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BusinessRuleCreateRequestModel {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("sys_id")
    private String id;
    @JsonProperty("client_callable")
    private String clientCallable = "false";
    private String template = "";
    @JsonProperty("action_insert")
    private String actionInsert = "";
    @JsonProperty("action_update")
    private String actionUpdate = "";
    @JsonProperty("action_delete")
    private String actionDelete = "";
    @JsonProperty("action_query")
    private String actionQuery = "";
    private String advanced = "true";
    @JsonProperty("change_fields")
    private String changeFields = "false";
    private String description = "";
    private String when = "";
    @JsonProperty("is_rest")
    private String isRest = "false";
    private String order = "";
    @JsonProperty("add_message")
    private String addMessage = "false";
    private String active = "true";
    private String message = "";
    private String priority = "";
    private String script = "";
    @JsonProperty("abort_action")
    private String abortAction = "false";
    @JsonProperty("execute_function")
    private String executeFunction = "false";
    @JsonProperty("filter_condition")
    private String filterCondition = "";
    private String condition = "";
    private String name = "";

    public String getClientCallable() {
        return clientCallable;
    }

    public void setClientCallable(String clientCallable) {
        this.clientCallable = clientCallable;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getActionInsert() {
        return actionInsert;
    }

    public void setActionInsert(String actionInsert) {
        this.actionInsert = actionInsert;
    }

    public String getActionUpdate() {
        return actionUpdate;
    }

    public void setActionUpdate(String actionUpdate) {
        this.actionUpdate = actionUpdate;
    }

    public String getActionDelete() {
        return actionDelete;
    }

    public void setActionDelete(String actionDelete) {
        this.actionDelete = actionDelete;
    }

    public String getActionQuery() {
        return actionQuery;
    }

    public void setActionQuery(String actionQuery) {
        this.actionQuery = actionQuery;
    }

    public String getAdvanced() {
        return advanced;
    }

    public void setAdvanced(String advanced) {
        this.advanced = advanced;
    }

    public String getChangeFields() {
        return changeFields;
    }

    public void setChangeFields(String changeFields) {
        this.changeFields = changeFields;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWhen() {
        return when;
    }

    public void setWhen(String when) {
        this.when = when;
    }

    public String getIsRest() {
        return isRest;
    }

    public void setIsRest(String isRest) {
        this.isRest = isRest;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getAddMessage() {
        return addMessage;
    }

    public void setAddMessage(String addMessage) {
        this.addMessage = addMessage;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public String getAbortAction() {
        return abortAction;
    }

    public void setAbortAction(String abortAction) {
        this.abortAction = abortAction;
    }

    public String getExecuteFunction() {
        return executeFunction;
    }

    public void setExecuteFunction(String executeFunction) {
        this.executeFunction = executeFunction;
    }

    public String getFilterCondition() {
        return filterCondition;
    }

    public void setFilterCondition(String filterCondition) {
        this.filterCondition = filterCondition;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
