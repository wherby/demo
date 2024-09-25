package com.example.demo.incident;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;



@CrossOrigin("*")
@RestController
@RequestMapping(path = "api/v1/incident")
public class IncidentController {
    private final  IncidentService incidentService;
    @Autowired
    public IncidentController(IncidentService incidentService) {
        this.incidentService = incidentService;
    }


    @GetMapping()
    public List<Incident> getIncident(){
        return incidentService.getIncident();
    }

    @GetMapping("/{pageNo}/{recordCount}")
    public Page<Incident> getPagedIncident(@PathVariable int pageNo, @PathVariable int recordCount){
        return  incidentService.getPageIncident(pageNo,recordCount);
    }

    @PostMapping()
    public ResponseEntity<Incident> addIncident(@RequestBody Incident incident){
        Incident created=incidentService.addIncident(incident);
        return ResponseEntity.created(URI.create("/api/resources/" + created.getId())).body(created);
    }
    @DeleteMapping(path = "{incidentId}")
    public void deleteIncident(@PathVariable("incidentId") Long incidentId){
        incidentService.deleteIncident(incidentId);
    }

    @GetMapping(path ="{incidentId}" )
    public Incident getIncident(@PathVariable("incidentId") Long incidentId){
        return  incidentService.getIncident(incidentId);
    }

    @PutMapping("{incidentId}")
    public Incident updateIncident(@PathVariable("incidentId") Long incidentId,
                               @RequestBody Incident incident){
        return incidentService.updateIncident(incidentId,incident);
    }
}
