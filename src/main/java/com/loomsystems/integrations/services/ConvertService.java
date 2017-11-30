package com.loomsystems.integrations.services;

import com.loomsystems.integrations.domain.incidents.*;
import com.loomsystems.integrations.domain.incidents.servicenow.SNIncidentRequestModel;
import com.loomsystems.integrations.domain.incidents.servicenow.SNIncidentResponseModel;
import com.loomsystems.integrations.domain.incidents.servicenow.SNIncidentModel;
import com.loomsystems.integrations.domain.incidents.servicenow.SNIncidentWebHookUpdateResponseModel;

public class ConvertService {

    public SNIncidentRequestModel convert(IncidentCreateRequestModel input) {
        SNIncidentRequestModel output = new SNIncidentRequestModel();
        map(input, output);

        return output;
    }

    public SNIncidentRequestModel convert(IncidentUpdateRequestModel input) {
        SNIncidentRequestModel output = new SNIncidentRequestModel();
        map(input, output);
        output.setOpenedBy(input.getOpenedBy());
        output.setCallerId(input.getCallerId());

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
    }


}
