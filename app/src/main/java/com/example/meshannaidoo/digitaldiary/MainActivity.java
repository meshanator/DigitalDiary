package com.example.meshannaidoo.digitaldiary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Calendar c = Calendar.getInstance();
        final int year = c.get(Calendar.YEAR);
        final int month = c.get(Calendar.MONTH);
        final int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);

        File currentDirectory = getApplicationContext().getFilesDir();
        currentDirectory.mkdirs();
        String dayFileName = String.valueOf(year) + String.valueOf(month) + String.valueOf(dayOfMonth) + ".txt";
        final File file = new File(currentDirectory, dayFileName);


        Button todayButton = (Button)findViewById(R.id.MainButtonToday);

        if(!file.exists())
        {
            todayButton.setEnabled(true);
        }



        CalendarView calendarView = (CalendarView) this.findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                Intent link = new Intent(getApplicationContext(), DayActivity.class);
                link.putExtra("year", year);
                link.putExtra("month", month);
                link.putExtra("dayOfMonth", dayOfMonth);
                startActivity(link);
            }
        });
        todayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent link = new Intent(getApplicationContext(), DayActivity.class);
                link.putExtra("year", year);
                link.putExtra("month", month);
                link.putExtra("dayOfMonth", dayOfMonth);
                startActivity(link);
            }
        });
    }
}

