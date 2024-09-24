package com.example.demo.incident;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/incident")
public class IncidentController {
    private final  IncidentService incidentService;
    @Autowired
    public IncidentController(IncidentService incidentService) {
        this.incidentService = incidentService;
    }


    @GetMapping("/")
    public List<Incident> getIncident(){
        return incidentService.getIncident();
    }

    @PostMapping("/")
    public void addIncident(@RequestBody Incident incident){
        incidentService.addIncident(incident);
    }
    @DeleteMapping(path = "{incidentId}")
    public void deleteIncident(@PathVariable("incidentId") Long incidentId){
        incidentService.deleteIncident(incidentId);
    }
}
