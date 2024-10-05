package com.bytemonk.IncidentReports.entity;

import com.bytemonk.IncidentReports.utils.SeverityLevel;
import com.bytemonk.IncidentReports.utils.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Incident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SeverityLevel severityLevel;
    @Column(nullable = false)
    private LocalDate incidentDate;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;
}
