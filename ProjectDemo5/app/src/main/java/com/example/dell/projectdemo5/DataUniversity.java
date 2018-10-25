package com.example.dell.projectdemo5;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class DataUniversity  {
    private ArrayList<University> universityList;
    private Context context;
    private static  final String link = "https://files.fm/down.php?cf&i=cmttqfcv&n=listTruongDH.txt";
    public DataUniversity(Context context)
    {
        this.context = context;
        universityList = new ArrayList<>();
        DownloadFile downloadFile = new DownloadFile();
        downloadFile.execute();
    }
    public DataUniversity(ArrayList<University> list)
    {
        this.universityList = list;
    }

    public ArrayList<University> getUniversityList() {
        return universityList;
    }

    public DataUniversity getInstance()
    {
        return this;
    }
    @SuppressLint("StaticFieldLeak")
    private class DownloadFile extends AsyncTask < URL, Void, Void >
    {
        @Override
        protected Void doInBackground(URL... urls) {
            getUniversity();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast toast = Toast.makeText(context,"Loaded",Toast.LENGTH_SHORT);
            toast.show();
        }

        private void getUniversity()
        {
            URL murl = null;
            try {
                murl = new URL(link);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            try {
                assert murl != null;
                URLConnection con = murl.openConnection();
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String textInLine;
                int count = 0;
                while ((textInLine = br.readLine()) != null) {
                    University u = new University(textInLine);
                    u.setZindex(count++);
                    universityList.add(u);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
