package com.example.meshannaidoo.digitaldiary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Scroller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent i = getIntent();
        String year = String.valueOf(i.getIntExtra("year", 0));
        String month = String.valueOf(i.getIntExtra("month", 0));
        String dayOfMonth = String.valueOf(i.getIntExtra("dayOfMonth", 0));

        File currentDirectory = getApplicationContext().getFilesDir();
        currentDirectory.mkdirs();
        String dayFileName = String.valueOf(year) + String.valueOf(month) + String.valueOf(dayOfMonth) + ".txt";
        final File file = new File(currentDirectory, dayFileName);
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

        }

        contents = text.toString();

        setContentView(R.layout.activity_day);

        EditText content = (EditText)this.findViewById(R.id.DayTextMain);
        content.setText(contents);

        content.setScroller(new Scroller(getApplicationContext()));
        content.setVerticalScrollBarEnabled(true);

        Button save = (Button)this.findViewById(R.id.DayButtonSave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText content = (EditText)findViewById(R.id.DayTextMain);
                Log.d("Meshan", content.getText().toString());
                Save(file, content.getText().toString());
                Intent link = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(link);
                finish();
            }
        });
    }

    public void Save(File f, String newContents){
        FileWriter fr = null;
        try {
            fr = new FileWriter(f);
            fr.write(newContents);
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
