package com.project.Web_Project.service.validation;

import com.project.Web_Project.dto.Reservation;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.text.SimpleDateFormat;
import java.util.Date;
@Component
public class ReservationValidation {

    private Date getMaxDate(){
        Date maxDate = new Date();
        maxDate.setDate(maxDate.getDate()+30);
        return maxDate;
    }
    private Date getMinDate(){
        Date minDate = new Date();
        minDate.setDate(minDate.getDate()+2);
        return minDate;
    }
    private boolean isNumOfDaysValid(int numOfDays){
        return numOfDays >2 && numOfDays <= 30;
    }

    private boolean isNumOfChildrenValid(int numOfChildren){
        return numOfChildren < 11 && numOfChildren >= 0;
    }
    private boolean isNumOfAdultsValid(int numOfAdults){
        return numOfAdults < 11 && numOfAdults > 0;
    }
    private boolean isPriceValid(int price){
        return price >= 5000 && price <= 100000;
    }
    private boolean isAccommodationValid(String accommondation){
        return accommondation != null;
    }
    private boolean isTownValid(String town){
        return town != null;
    }


    public boolean setUpReservationValidation(Reservation reservation) {
        return isNumOfDaysValid(reservation.getNumOfDays()) &&
                isNumOfChildrenValid(reservation.getNumOfChildren()) &&
                isNumOfAdultsValid(reservation.getNumOfAdults()) &&
                isAccommodationValid(reservation.getAccommodation()) &&
                isPriceValid(reservation.getPrice()) &&
                isTownValid(reservation.getTown());
    }


    public void setMinAndMaxDates(Model model){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        model.addAttribute("minDate", dateFormat.format(getMinDate()));
        model.addAttribute("maxDate", dateFormat.format(getMaxDate()));
    }
}
