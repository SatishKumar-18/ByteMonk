package com.bytemonk.IncidentReports.service;

import com.bytemonk.IncidentReports.dto.IncidentRequest;
import com.bytemonk.IncidentReports.entity.Incident;
import com.bytemonk.IncidentReports.repository.IncidentRepository;
import com.bytemonk.IncidentReports.utils.SeverityLevel;
import com.bytemonk.IncidentReports.utils.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Collections;

import static com.bytemonk.IncidentReports.utils.IncidentTestUtils.createValidRequest;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class IncidentServiceTest {

    @Mock
    private IncidentRepository incidentRepo;

    @InjectMocks
    private IncidentService incidentService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateIncident_ValidInput_ShouldSaveIncident(){
        IncidentRequest validRequest = createValidRequest();

        when(incidentRepo.existsByTitle(validRequest.getTitle())).thenReturn(false);

        Incident savedIncident = new Incident(1L, "Valid Incident", "his is a valid incident description", SeverityLevel.LOW, LocalDate.now(), Status.PENDING);
        when(incidentRepo.save(any(Incident.class))).thenReturn(savedIncident);

        Incident createdIncident = incidentService.createIncident(validRequest);


        Assertions.assertNotNull(createdIncident);
        Assertions.assertEquals(validRequest.getTitle(), createdIncident.getTitle());
    }

    @Test
    void testCreateIncident_InvalidTitleLength_ShouldThrowException(){
        IncidentRequest invalidRequest = createValidRequest();
        invalidRequest.setTitle("Invalid");

        Assertions.assertThrows(IllegalArgumentException.class, () ->
                incidentService.createIncident(invalidRequest)
        );
    }

    @Test
    void testGetAllIncidentBySeverity_NoIncidents_ShouldThrowException() {
        SeverityLevel severityLevel = SeverityLevel.HIGH;

        when(incidentRepo.findBySeverityLevel(severityLevel)).thenReturn(Collections.emptyList());

        Assertions.assertThrows(RuntimeException.class, () ->
            incidentService.getAllIncidentBySeverity(severityLevel)
        );
    }
}
