package com.loomsystems.integrations.services;

import com.loomsystems.integrations.DataStorage;
import com.loomsystems.integrations.domain.enums.IncidentOperation;
import com.loomsystems.integrations.domain.incidents.*;
import com.loomsystems.integrations.domain.servicenow.incidents.SNIncidentRequestModel;
import com.loomsystems.integrations.domain.servicenow.incidents.SNIncidentResponseModel;
import com.loomsystems.integrations.domain.servicenow.incidents.SNIncidentWebHookUpdateResponseModel;
import org.apache.http.auth.AuthenticationException;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public class IncidentService {

    private RequestService requestService = new RequestService();
    private ConvertService convertService= new ConvertService();

    public Optional<IncidentCreateResponseModel> create(IncidentCreateRequestModel request) {
        SNIncidentRequestModel snRequest = convertService.convert(request);

        try {
            return requestService
                    .sendPost("/api/now/table/incident", snRequest, SNIncidentResponseModel.class)
            .map(snResponse -> {
                IncidentCreateResponseModel responseModel = convertService.convertToCreateResponse(snResponse);
                Incident incident = convertService.convert(responseModel);
                DataStorage.getInstance().add(incident);
                return responseModel;
            });
        } catch (IOException | AuthenticationException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }


    public Optional<IncidentUpdateResponseModel> update(String id, IncidentUpdateRequestModel request) {
        return DataStorage.getInstance().get(id)
                .map(incident -> {

                    SNIncidentRequestModel snRequest = convertService.convert(request);

                    snRequest.setAssignedTo(incident.getAssignedTo());
                    snRequest.setCallerId(incident.getCallerId());
                    snRequest.setOpenedBy(incident.getOpenedBy());
                    return snRequest;
                })
                .flatMap(snRequest -> {
                    try {
                        return requestService
                                .sendPut("/api/now/table/incident/" + id, snRequest, SNIncidentResponseModel.class)
                                .map(snResponse -> {
                                    IncidentUpdateResponseModel responseModel = convertService.convertToUpdateResponse(snResponse);
                                    Incident incident = convertService.convert(responseModel);
                                    DataStorage.getInstance().update(incident);
                                    return responseModel;
                                });
                    } catch (IOException | AuthenticationException e) {
                        e.printStackTrace();
                        return Optional.empty();
                    }
                });
    }

    public Optional<IncidentUpdateResponseModel> updateEvent(SNIncidentWebHookUpdateResponseModel updateModel) {
        IncidentUpdateResponseModel responseModel = convertService.convert(updateModel);
        Incident incident = convertService.convert(responseModel);
        IncidentOperation operation = IncidentOperation.UPDATE;
        if(updateModel.getClosedAt() != null && !updateModel.getClosedAt().isEmpty()) {
            operation = IncidentOperation.CLOSE;
        }
//        IncidentOperation operation = IncidentOperation.fromString(updateModel.getOperation());
        switch (operation) {
            case UPDATE: {
                if (DataStorage.getInstance().update(incident)) {
                    return Optional.of(responseModel);
                }

                return Optional.empty();
            }
            case CLOSE: {
                if (DataStorage.getInstance().delete(incident)) {
                    return Optional.of(responseModel);
                }

                return Optional.empty();
            }
            default:
                return Optional.empty();
        }
    }

    public List<IncidentResponseModel> list() {
        return DataStorage.getInstance().list().stream().map(convertService::convert).collect(toList());
    }

    public List<IncidentResponseModel> listClosed() {
        return DataStorage.getInstance().listClosed().stream().map(convertService::convert).collect(toList());
    }
}
