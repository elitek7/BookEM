package com.edevs.bookem;

import androidx.annotation.NonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Reservation {
    protected int reservation_id;
    protected LocalDate start_date;
    protected LocalDate end_date;

    public Reservation(int reservation_id, LocalDate start_date, LocalDate end_date){
        this.reservation_id = reservation_id;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    public int getReservationId() {return reservation_id; }

    public void setReservationId(int reservation_id) {
        this.reservation_id = reservation_id;
    }

    public LocalDate getStartDate() {
        return start_date;
    }

    public void setStartDate(LocalDate start_date) {this.start_date = start_date;}

    public LocalDate getEndDate() {return end_date; }

    public void setEndDate(LocalDate end_date) {this.end_date = end_date;}

    @NonNull
    public String toString(){
        return ("Start Date: " + start_date + " End Date: " + end_date );
    }
}
