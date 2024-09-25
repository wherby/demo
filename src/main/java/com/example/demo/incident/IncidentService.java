package com.example.demo.incident;

import org.hibernate.annotations.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

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

    @Cacheable(value = "incidents",key = "{#pageNo,#recordCount}")
    public Page<Incident> getPageIncident( int pageNo,  int recordCount){
        Pageable pageable = PageRequest.of(pageNo,recordCount);
        //System.out.println("Get page from db ");
        return incidentRepository.findAll(pageable);
    }

    @CacheEvict(value = "incidents",allEntries = true)
    public Incident addIncident(Incident incident) {
        incident.setCreateTime(LocalDate.now());
        incident.setStatus("Created");
        if( incident.getId()!=null){
            throw new IllegalStateException("Id should be set by db");
        }
        return incidentRepository.save(incident);
    }

    @Cacheable(value = "incidents", key="#incidentId")
    public Incident getIncident(Long incidentId){
        Incident incident= incidentRepository.findById(incidentId).orElseThrow(
                ()->new ResourceNotExisted(notFoundMsg(incidentId))
        );
        //System.out.println("Get from db " + incidentId);
        return incident;
    }
    @CacheEvict(value = "incidents",allEntries = true)
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

    @CacheEvict(value = "incidents",allEntries = true)
    public Incident updateIncident(Long incidentId, Incident updatedIncident){
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
        return incident;
    }
}
