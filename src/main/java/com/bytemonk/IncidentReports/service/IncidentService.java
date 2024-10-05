package com.bytemonk.IncidentReports.service;

import com.bytemonk.IncidentReports.dto.IncidentRequest;
import com.bytemonk.IncidentReports.dto.UpdateRequest;
import com.bytemonk.IncidentReports.entity.Incident;
import com.bytemonk.IncidentReports.repository.IncidentRepository;
import com.bytemonk.IncidentReports.utils.SeverityLevel;
import com.bytemonk.IncidentReports.utils.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class IncidentService {

    @Autowired
    private IncidentRepository incidentRepo;

    public Incident createIncident(IncidentRequest incidentRequest){


        String title = incidentRequest.getTitle();

        //Check title length
        if(title.length() < 10){
            throw new IllegalArgumentException("Incident title must be at least 10 characters long.");
        }
        //Validate title
        if(incidentRepo.existsByTitle(title) && incidentRepo.existsByIncidentDate(incidentRequest.getIncidentDate())){
            throw new IllegalArgumentException("Title already exists");
        }

        //Check for date
        LocalDate currentDate = LocalDate.now();
        LocalDate thirtyDaysAgo = currentDate.minusDays(30);

        if(incidentRequest.getIncidentDate().isBefore(thirtyDaysAgo) ||
                incidentRequest.getIncidentDate().isAfter(currentDate)){
            throw new IllegalArgumentException("Incident date must be within the last 30 days or today.");
        }

        //Check for severity
        SeverityLevel inputSeverity = incidentRequest.getSeverityLevel();
        boolean validateSeverity = inputSeverity == SeverityLevel.LOW ||
                inputSeverity == SeverityLevel.MEDIUM ||
                inputSeverity == SeverityLevel.HIGH;

        if(!validateSeverity){
            throw new IllegalArgumentException("Invalid severity level");
        }

        //create data
        Incident incident = Incident.builder()
                .title(title)
                .description(incidentRequest.getDescription())
                .severityLevel(incidentRequest.getSeverityLevel())
                .incidentDate(incidentRequest.getIncidentDate())
                .status(Status.PENDING)
                .build();

        return incidentRepo.save(incident);
    }

    public Incident getIncidentById(Long id){

        return incidentRepo.findById(id).orElseThrow(() -> new RuntimeException("Incident Not Found"));
    }

    public Incident updateIncident(UpdateRequest updateRequest, Incident incident){

        String title = updateRequest.getTitle();

        //Check title length
        if(title.length() < 10){
            throw new IllegalArgumentException("Incident title must be at least 10 characters long.");
        }

        //Validate title
        if(!title.equals(incident.getTitle()) && incidentRepo.existsByTitle(title) && incidentRepo.existsByIncidentDate(incident.getIncidentDate())){
                throw new IllegalArgumentException("Title already exists");
        }


        //Check for date
        LocalDate currentDate = LocalDate.now();
        LocalDate thirtyDaysAgo = currentDate.minusDays(30);

        if(updateRequest.getIncidentDate().isBefore(thirtyDaysAgo) ||
                updateRequest.getIncidentDate().isAfter(currentDate)){
            throw new IllegalArgumentException("Incident date must be within the last 30 days or today.");
        }

        //Check for severity
        SeverityLevel inputSeverity = updateRequest.getSeverityLevel();
        boolean validateSeverity = inputSeverity == SeverityLevel.LOW ||
                inputSeverity == SeverityLevel.MEDIUM ||
                inputSeverity == SeverityLevel.HIGH;

        if(!validateSeverity){
            throw new IllegalArgumentException("Invalid severity level");
        }

        //update and save
        incident.setId(incident.getId());
        incident.setTitle(updateRequest.getTitle());
        incident.setDescription(updateRequest.getDescription());
        incident.setSeverityLevel(updateRequest.getSeverityLevel());
        incident.setIncidentDate(updateRequest.getIncidentDate());
        incident.setStatus(updateRequest.getStatus());

        return incidentRepo.save(incident);
    }

    public List<Incident> getAllIncident(){
        return incidentRepo.findAll();
    }

    public List<Incident> getAllIncidentBySeverity(SeverityLevel severityLevel){
        List<Incident> bySeverityLevel = incidentRepo.findBySeverityLevel(severityLevel);

        if(bySeverityLevel.isEmpty()){
            throw new RuntimeException("No Incident of severity "+severityLevel);
        }

        return bySeverityLevel;
    }
}
