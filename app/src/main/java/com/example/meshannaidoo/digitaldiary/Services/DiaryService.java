package com.example.meshannaidoo.digitaldiary.Services;

import android.net.Uri;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.support.v4.content.FileProvider;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by meshannaidoo on 2017/02/12.
 */

public class DiaryService {
    public DiaryService(){

    }

    public String GetDay(int year, int month, int dayOfTheMonth){
        File currentDirectory = Environment.getExternalStorageDirectory();
        currentDirectory.mkdirs();
        String dayFileName = String.valueOf(year) + String.valueOf(month) + String.valueOf(dayOfTheMonth) + ".txt";
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

        return contents;
    }
}
