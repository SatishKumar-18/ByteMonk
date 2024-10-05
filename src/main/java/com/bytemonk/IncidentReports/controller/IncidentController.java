package com.bytemonk.IncidentReports.controller;

import com.bytemonk.IncidentReports.dto.IncidentRequest;
import com.bytemonk.IncidentReports.dto.UpdateRequest;
import com.bytemonk.IncidentReports.entity.Incident;
import com.bytemonk.IncidentReports.service.IncidentService;
import com.bytemonk.IncidentReports.utils.SeverityLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/incidents")
public class IncidentController {

    @Autowired
    private IncidentService incidentService;

    @PostMapping("/create-incident")
    public ResponseEntity<?> createIncident(@RequestBody IncidentRequest incidentRequest){
        try{
            Incident incident = incidentService.createIncident(incidentRequest);

            return new ResponseEntity<>(incident, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-incident/id/{id}")
    public ResponseEntity<?> getIncidentById(@PathVariable Long id){
        try{
            Incident incidentById = incidentService.getIncidentById(id);

            return new ResponseEntity<>(incidentById, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllIncident(){
        List<Incident> allIncident = incidentService.getAllIncident();

        return new ResponseEntity<>(allIncident, HttpStatus.OK);
    }

    @GetMapping("/get-all/{severity}")
    public ResponseEntity<?> getAllIncidentBySeverity(@PathVariable SeverityLevel severity){
        try {
            List<Incident> allIncidentBySeverity = incidentService.getAllIncidentBySeverity(severity);

            return new ResponseEntity<>(allIncidentBySeverity, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/update-incident/id/{id}")
    public ResponseEntity<?> updateIncident(@PathVariable Long id, @RequestBody UpdateRequest updateRequest){
        
        try{
            Incident existingIncident = incidentService.getIncidentById(id);

            Incident incident = incidentService.updateIncident(updateRequest, existingIncident);
            
            return new ResponseEntity<>(incident, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}