package com.example.meshannaidoo.digitaldiary.Services;

import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.support.v4.content.FileProvider;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by meshannaidoo on 2017/02/12.
 */

public class DiaryService {
    public DiaryService(){

    }

    public String GetDay(int year, int month, int dayOfTheMonth){
        FileProvider t = new FileProvider();
        String file = "content://" + String.valueOf(year) + String.valueOf(month) + String.valueOf(dayOfTheMonth);
        //Uri u = FileProvider.getUriForFile(this, "asd", File.createTempFile("", ""))
        ParcelFileDescriptor pfd;
        try {
            pfd = t.openFile(Uri.parse(file), "MODE_READ_WRITE");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        return null;
    }
}
