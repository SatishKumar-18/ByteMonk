package com.bytemonk.IncidentReports.repository;

import com.bytemonk.IncidentReports.entity.Incident;
import com.bytemonk.IncidentReports.utils.SeverityLevel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface IncidentRepository extends JpaRepository<Incident, Long> {
    boolean existsByTitle(String title);
    boolean existsByIncidentDate(LocalDate incidentDate);
    List<Incident> findBySeverityLevel(SeverityLevel severity);
}
