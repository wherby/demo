package com.example.demo.incident;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

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
        incident.setCreateTime(LocalDate.now());
        incident.setStatus("Created");
        if( incident.getId()!=null){
            throw new IllegalStateException("Id should be set by db");
        }
        incidentRepository.save(incident);
    }

    public Incident getIncident(Long incidentId){
        Incident incident= incidentRepository.findById(incidentId).orElseThrow(
                ()->new ResourceNotExisted(notFoundMsg(incidentId))
        );
        return incident;
    }
    public void deleteIncident(Long incidentId) {
        boolean exist= incidentRepository.existsById(incidentId);
        if(!exist){
            throw  new ResourceNotExisted(notFoundMsg(incidentId));
        }
        incidentRepository.deleteById(incidentId);
    }

    private static String notFoundMsg(Long incidentId) {
        return "Incident with Id " + incidentId + " does not exist";
    }

    public void updateIncident(Long incidentId, Incident updatedIncident){
        Incident incident = incidentRepository.findById(incidentId).orElseThrow(
                ()-> new ResourceNotExisted(notFoundMsg(incidentId))
        );
        incident.setPriority(updatedIncident.getPriority());
        incident.setDescription(updatedIncident.getDescription());
        incident.setType(updatedIncident.getType());
        incident.setUpdateBy(updatedIncident.getUpdateBy());
        incident.setUpdateTime(LocalDate.now());
        incident.setStatus(updatedIncident.getStatus());
        incidentRepository.save(incident);
    }
}
