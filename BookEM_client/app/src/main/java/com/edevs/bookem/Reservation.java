package com.edevs.bookem;

import androidx.annotation.NonNull;

public abstract class Reservation {
    protected int reservation_id;
    protected String start_date;
    protected String end_date;

    public Reservation(int reservation_id, String start_date, String end_date){
        this.reservation_id = reservation_id;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    public int getReservationId() {return reservation_id; }

    public void setReservationId(int reservation_id) {
        this.reservation_id = reservation_id;
    }

    public String getStartDate() {
        return start_date;
    }

    public void setStartDate(String start_date) {this.start_date = start_date;}

    public String getEndDate() {return end_date; }

    public void setEndDate(String end_date) {this.end_date = end_date;}

    @NonNull
    public abstract String toString();
}
