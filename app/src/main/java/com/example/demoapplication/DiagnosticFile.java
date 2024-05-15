package com.example.demoapplication;

import android.content.Context;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class DiagnosticFile {
    String name;
    Context context;

    public DiagnosticFile(Context context, String logFileName) {
        this.context = context;
        this.name = "log_" + logFileName + ".csv";
        this.name = this.name.replace(" ", "_");
    }

    public void appendLog(String text){
        appendLog(text, "");
    }

    public void appendLog(String text, String action) {
        File logFile = new File(context.getExternalFilesDir(null), name);

        if (!logFile.exists()) {
            try {
                logFile.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        try {
            String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS").format(new java.util.Date());
            //BufferedWriter for performance, true to set append to file flag
            BufferedWriter buf = new BufferedWriter(new FileWriter(logFile, true));
            buf.append(timeStamp + "," + (action == null ? "" : action) + "," + text.replace(' ',','));
            buf.newLine();
            buf.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
