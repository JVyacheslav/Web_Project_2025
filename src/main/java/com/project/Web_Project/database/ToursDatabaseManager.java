package com.project.Web_Project.database;

import com.project.Web_Project.dto.Tour;
import com.project.Web_Project.interfaces.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ToursDatabaseManager {
    private final TourRepository tourRepository;
    @Autowired
    ToursDatabaseManager(TourRepository tourRepository){
        this.tourRepository=tourRepository;
    }

    public List<Tour> selectAll(){
        return tourRepository.findAll();
    }
    public Optional<Tour> selectById(long id){
        return Optional.ofNullable(tourRepository.findById(id).orElse(null));
    }
}
