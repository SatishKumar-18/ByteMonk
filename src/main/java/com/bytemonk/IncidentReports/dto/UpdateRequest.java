package com.bytemonk.IncidentReports.dto;

import com.bytemonk.IncidentReports.utils.SeverityLevel;
import com.bytemonk.IncidentReports.utils.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRequest {

    @NonNull
    private String title;
    @NonNull
    private String description;
    @NonNull
    private SeverityLevel severityLevel;
    @NonNull
    private LocalDate incidentDate;
    @NonNull
    private Status status;
}
