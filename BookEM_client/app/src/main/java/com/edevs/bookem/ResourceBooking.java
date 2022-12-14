package com.edevs.bookem;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Objects;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class ResourceBooking extends AppCompatActivity implements View.OnClickListener{

    TextView fromDateText;
    TextView toDateText;
    Button fromBtn;
    Button toBtn;
    Button doneBtn;
    static LocalDate fromDate;
    static LocalDate toDate;
    SimpleDateFormat simpleDateFormat;
    int year, month, day, year2, month2, day2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.resource_view);

        doneBtn = (Button) findViewById(R.id.doneBtn);
        fromBtn = (Button) findViewById(R.id.fromBtn);
        toBtn = (Button) findViewById(R.id.toBtn);
        fromBtn.setOnClickListener(this);
        toBtn.setOnClickListener(this);
        fromDateText = (TextView) findViewById(R.id.fromDateText);
        toDateText = (TextView) findViewById(R.id.toDateText);
        simpleDateFormat = new SimpleDateFormat("dd MM yyyy", Locale.US);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fromBtn:
            {// on below line we are getting
                // the instance of our calendar.
                final Calendar c = Calendar.getInstance();

                // on below line we are getting
                // our day, month and year.
                year = c.get(Calendar.YEAR);
                month = c.get(Calendar.MONTH);
                day = c.get(Calendar.DAY_OF_MONTH);

                // on below line we are creating a variable for date picker dialog.
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        // on below line we are passing context.
                        ResourceBooking.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // on below line we are setting date to our text view.
                                fromDateText.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            }
                        },
                        // on below line we are passing year,
                        // month and day for selected date in our date picker.
                        year, month, day);
                // at last we are calling show to
                // display our date picker dialog.
                datePickerDialog.show();
                fromDate = LocalDate.of(year, month, day);}
            break;
            case R.id.toBtn:
            {// on below line we are getting
                // the instance of our calendar.
                final Calendar c2 = Calendar.getInstance();

                // on below line we are getting
                // our day, month and year.
                year2 = c2.get(Calendar.YEAR);
                month2 = c2.get(Calendar.MONTH);
                day2 = c2.get(Calendar.DAY_OF_MONTH);

                // on below line we are creating a variable for date picker dialog.
                DatePickerDialog datePickerDialog2 = new DatePickerDialog(
                        // on below line we are passing context.
                        ResourceBooking.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // on below line we are setting date to our text view.
                                toDateText.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            }
                        },
                        // on below line we are passing year,
                        // month and day for selected date in our date picker.
                        year2, month2, day2);
                // at last we are calling show to
                // display our date picker dialog.
                datePickerDialog2.show();
                toDate = LocalDate.of(year2, month2, day2);}
            break;
            default:
                break;
        }
    }

    public void makeReservation(View v)
    {
        Link.addReservation(ResourceBooking.this, fromDate, toDate, this);
        Toast.makeText(this, "Success!", Toast.LENGTH_SHORT).show();
    }
}
