package com.project.Web_Project.database;

import com.project.Web_Project.dto.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface TourRepository extends JpaRepository<Tour, Long> {
    Optional<Tour> findById(Long id);
    List<Tour> findAll();
}