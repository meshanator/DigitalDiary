package com.example.meshannaidoo.digitaldiary;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.widget.CalendarView;

import com.example.meshannaidoo.digitaldiary.Services.DiaryService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CalendarView calendarView = (CalendarView) this.findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {


                File currentDirectory = getApplicationContext().getFilesDir();
                currentDirectory.mkdirs();
                String dayFileName = String.valueOf(year) + String.valueOf(month) + String.valueOf(dayOfMonth) + ".txt";
                File file = new File(currentDirectory, dayFileName);
                String contents = "";


                if(!file.exists())
                {
                    try {
                        file.createNewFile();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                StringBuilder text = new StringBuilder();

                try {
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    String line;

                    while ((line = br.readLine()) != null) {
                        text.append(line);
                        text.append('\n');
                    }
                    br.close();
                }
                catch (IOException e) {
                    //You'll need to add proper error handling here
                }
                contents = text.toString();


                FileWriter fr = null;
                try {
                    fr = new FileWriter(file);
                    fr.write(contents + " " + String.valueOf(System.nanoTime()));
                } catch (IOException e) {
                    e.printStackTrace();
                }finally{
                    //close resources
                    try {
                        fr.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }




                Log.d("M", contents);
            }
        });
    }
}

