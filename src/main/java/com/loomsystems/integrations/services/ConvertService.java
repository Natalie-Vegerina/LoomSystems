package com.loomsystems.integrations.services;

import com.loomsystems.integrations.domain.incidents.*;
import com.loomsystems.integrations.domain.servicenow.incidents.SNIncidentRequestModel;
import com.loomsystems.integrations.domain.servicenow.incidents.SNIncidentResponseModel;
import com.loomsystems.integrations.domain.servicenow.incidents.SNIncidentModel;
import com.loomsystems.integrations.domain.servicenow.incidents.SNIncidentWebHookUpdateResponseModel;
import com.loomsystems.integrations.domain.servicenow.users.SNUserResponseModel;
import com.loomsystems.integrations.domain.users.UserResponseModel;

public class ConvertService {

    public SNIncidentRequestModel convert(IncidentCreateRequestModel input) {
        SNIncidentRequestModel output = new SNIncidentRequestModel();
        map(input, output);

        return output;
    }

    public SNIncidentRequestModel convert(IncidentUpdateRequestModel input) {
        SNIncidentRequestModel output = new SNIncidentRequestModel();
        map(input, output);

        return output;
    }

    public IncidentCreateResponseModel convertToCreateResponse(SNIncidentResponseModel input) {
        IncidentCreateResponseModel output = new IncidentCreateResponseModel();
        map(input, output);
        output.setCallerId(input.getCallerId().getValue());
        output.setOpenedBy(input.getOpenedBy().getValue());
        output.setId(input.getId());
        output.setUpdatedOn(input.getUpdatedOn());
        output.setAssignedTo(input.getAssignedTo().getValue());
        output.setAssignmentGroup(input.getAssignmentGroup().getValue());


        return output;
    }

    public IncidentUpdateResponseModel convertToUpdateResponse(SNIncidentResponseModel input) {
        IncidentUpdateResponseModel output = new IncidentUpdateResponseModel();
        map(input, output);
        output.setCallerId(input.getCallerId().getValue());
        output.setOpenedBy(input.getOpenedBy().getValue());
        output.setId(input.getId());
        output.setUpdatedOn(input.getUpdatedOn());
        output.setAssignedTo(input.getAssignedTo().getValue());
        output.setAssignmentGroup(input.getAssignmentGroup().getValue());

        return output;
    }

    public IncidentUpdateResponseModel convert(SNIncidentWebHookUpdateResponseModel input) {
        IncidentUpdateResponseModel output = new IncidentUpdateResponseModel();
        map(input, output);
        output.setCallerId(input.getCallerId());
        output.setOpenedBy(input.getOpenedBy());
        output.setClosedBy(input.getClosedBy());
        output.setClosedAt(input.getClosedAt());
        output.setId(input.getId());
        output.setUpdatedOn(input.getUpdatedOn());
        output.setAssignedTo(input.getAssignedTo());
        output.setAssignmentGroup(input.getAssignmentGroup());

        return output;
    }

    public Incident convert(IncidentCreateResponseModel input) {
        Incident output = new Incident();
        map(input, output);
        output.setCallerId(input.getCallerId());
        output.setOpenedBy(input.getOpenedBy());
        output.setId(input.getId());
        output.setUpdatedOn(input.getUpdatedOn());

        return output;
    }

    public Incident convert(IncidentUpdateResponseModel input) {
        Incident output = new Incident();
        map(input, output);
        output.setClosedBy(input.getClosedBy());
        output.setClosedAt(input.getClosedAt());

        return output;
    }

    public IncidentResponseModel convert(Incident input) {
        IncidentResponseModel output = new IncidentResponseModel();
        output.setActive(input.getActive());
        output.setCallerId(input.getCallerId());
        output.setCategory(input.getCategory());
        output.setImpact(input.getImpact());
        output.setIncidentState(input.getIncidentState());
        output.setNotify(input.getNotify());
        output.setNumber(input.getNumber());
        output.setOpenedBy(input.getOpenedBy());
        output.setOpenedAt(input.getOpenedAt());
        output.setPriority(input.getPriority());
        output.setSeverity(input.getSeverity());
        output.setShortDescription(input.getShortDescription());
        output.setState(input.getState());
        output.setUrgency(input.getUrgency());
        output.setId(input.getId());
        output.setUpdatedOn(input.getUpdatedOn());
        output.setAssignedTo(input.getAssignedTo());
        output.setAssignmentGroup(input.getAssignmentGroup());

        return output;
    }

    public UserResponseModel convert(SNUserResponseModel input) {
        UserResponseModel output = new UserResponseModel();
        output.setCity(input.getCity());
        output.setDateFormat(input.getDateFormat());
        output.setEmail(input.getEmail());
        output.setFirstName(input.getFirstName());
        output.setHomePhone(input.getHomePhone());
        output.setId(input.getId());
        output.setLastName(input.getLastName());
        output.setMiddleName(input.getMiddleName());
        output.setMobilePhone(input.getMobilePhone());
        output.setName(input.getName());
        output.setPhone(input.getPhone());
        output.setState(input.getState());
        output.setStreet(input.getStreet());
        output.setTimeFormat(input.getTimeFormat());
        output.setTimeZone(input.getTimeZone());
        output.setUserName(input.getUserName());
        output.setZip(input.getZip());

        return output;
    }

    private void map(IncidentCreateRequestModel input, SNIncidentRequestModel output) {
        output.setActive(input.getActive());
        output.setCategory(input.getCategory());
        output.setImpact(input.getImpact());
        output.setIncidentState(input.getIncidentState());
        output.setNotify(input.getNotify());
        output.setNumber(input.getNumber());
        output.setOpenedAt(input.getOpenedAt());
        output.setPriority(input.getPriority());
        output.setSeverity(input.getSeverity());
        output.setShortDescription(input.getShortDescription());
        output.setState(input.getState());
        output.setUrgency(input.getUrgency());
        output.setAssignedTo(input.getAssignedTo());
        output.setAssignmentGroup(input.getAssignmentGroup());
        output.setOpenedBy(input.getOpenedBy());
        output.setCallerId(input.getCallerId());
    }


    private void map(SNIncidentModel input, IncidentCreateResponseModel output) {
        output.setActive(input.getActive());
        output.setCategory(input.getCategory());
        output.setImpact(input.getImpact());
        output.setIncidentState(input.getIncidentState());
        output.setNotify(input.getNotify());
        output.setNumber(input.getNumber());
        output.setOpenedAt(input.getOpenedAt());
        output.setPriority(input.getPriority());
        output.setSeverity(input.getSeverity());
        output.setShortDescription(input.getShortDescription());
        output.setState(input.getState());
        output.setUrgency(input.getUrgency());
    }

    private void map(IncidentCreateResponseModel input, Incident output) {
        output.setActive(input.getActive());
        output.setCallerId(input.getCallerId());
        output.setCategory(input.getCategory());
        output.setImpact(input.getImpact());
        output.setIncidentState(input.getIncidentState());
        output.setNotify(input.getNotify());
        output.setNumber(input.getNumber());
        output.setOpenedBy(input.getOpenedBy());
        output.setOpenedAt(input.getOpenedAt());
        output.setPriority(input.getPriority());
        output.setSeverity(input.getSeverity());
        output.setShortDescription(input.getShortDescription());
        output.setState(input.getState());
        output.setUrgency(input.getUrgency());
        output.setId(input.getId());
        output.setUpdatedOn(input.getUpdatedOn());
        output.setAssignedTo(input.getAssignedTo());
        output.setAssignmentGroup(input.getAssignmentGroup());
    }


}
