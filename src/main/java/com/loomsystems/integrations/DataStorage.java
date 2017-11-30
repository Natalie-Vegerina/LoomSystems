package com.loomsystems.integrations;

import com.loomsystems.integrations.domain.incidents.Incident;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DataStorage {
    private List<Incident> incidents = new ArrayList<>();
    private List<Incident> closed = new ArrayList<>();

    private static DataStorage instance;

    public static DataStorage getInstance() {
        if (instance == null) {
            synchronized (DataStorage.class) {
                if (instance == null) {
                    instance = new DataStorage();
                }
            }
        }

        return instance;
    }

    public List<Incident> getIncidents() {
        return incidents;
    }

    public Optional<Incident> get(String id) {
        return incidents.stream().filter(x -> x.getId().equals(id)).findFirst();
    }

    public boolean add(Incident item) {
        boolean exists = incidents.stream()
                .anyMatch(x -> x.getId().equals(item.getId()));
        if(exists) {
            return false;
        }
        else {
            return incidents.add(item);
        }
    }

    public boolean update(Incident item) {
        boolean exists = incidents.removeIf(x -> x.getId().equals(item.getId()));
        if(exists) {
            return incidents.add(item);
        }

        return false;
    }

    public boolean delete(Incident item) {
        return incidents.stream()
                .filter(x -> x.getId().equals(item.getId()))
                .findFirst()
                .map(x -> {
                    incidents.remove(x);
                    closed.add(item);
                    return true;
                })
                .orElse(false);
    }

    public List<Incident> list() {
        return incidents;
    }

    public List<Incident> listClosed() {
        return closed;
    }

}
