package com.example.demo.incident;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Console;
import java.time.LocalDate;
import java.util.List;

@Service
public class IncidentService {
    private final IncidentRepository incidentRepository;
    @Autowired
    public IncidentService(IncidentRepository incidentRepository) {
        this.incidentRepository = incidentRepository;
    }

    public List<Incident> getIncident(){
        return incidentRepository.findAll();

    }

    public void addIncident(Incident incident) {
        if(incident.getId()>0){
            throw new IllegalStateException("Id should be set by db");
        }
        incidentRepository.save(incident);
    }

    public void deleteIncident(Long incidentId) {
        boolean exist= incidentRepository.existsById(incidentId);
        if(!exist){
            throw  new IllegalStateException("Incident with Id " + incidentId + " does not exist"   );
        }
        incidentRepository.deleteById(incidentId);
    }
}
