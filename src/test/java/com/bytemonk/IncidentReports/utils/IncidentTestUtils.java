package com.bytemonk.IncidentReports.utils;

import com.bytemonk.IncidentReports.dto.IncidentRequest;

import java.time.LocalDate;

public class IncidentTestUtils {

    public static IncidentRequest createValidRequest(){
        IncidentRequest request = new IncidentRequest();
        request.setTitle("Valid Incident");
        request.setDescription("This is a valid incident description");
        request.setSeverityLevel(SeverityLevel.LOW);
        request.setIncidentDate(LocalDate.now());

        return request;
    }
}
