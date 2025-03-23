package com.project.Web_Project.service;

import com.project.Web_Project.database.ToursDatabaseManager;
import com.project.Web_Project.dto.Tour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class TourActions {
    @Autowired
    private ToursDatabaseManager toursDatabaseManager;

    public List<Tour> getToursByParam(int param){
        List<Tour> tours = toursDatabaseManager.selectAll();
        List<Tour> result = new ArrayList<>();
        int i = (param-1)*6, k = param*6;
        while(i >= 0 && i < tours.size() && i < k) {
            result.add(tours.get(i));
            i++;
        }
        return result;
    }
}
