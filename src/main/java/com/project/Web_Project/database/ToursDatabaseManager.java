package com.project.Web_Project.database;

import com.project.Web_Project.dto.Tour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
