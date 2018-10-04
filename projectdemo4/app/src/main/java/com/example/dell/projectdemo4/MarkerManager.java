package com.example.dell.projectdemo4;

import android.content.SharedPreferences;
import android.widget.Toast;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import java.util.ArrayList;

public class MarkerManager {
    private Gson gson;
    private int count;
    private ArrayList<MyMarker> arrayMarker;
    private ArrayList<Marker> listMarker;

    public ArrayList<MyMarker> getArrayMarker() {
        return arrayMarker;
    }

    private MyMarker currentMyMarker;
    public MarkerManager(MainActivity mainActivity) {
        arrayMarker = new ArrayList<>();
        listMarker = new ArrayList<>();

        count = mainActivity.sharedPreferences.getInt("count",0);
        gson = new Gson();
        if ( count > 0)
        {
            for (int i =0;i < count;i++){
                MyMarker mm = gson.fromJson(mainActivity.sharedPreferences.getString("marker"+String.valueOf(i), ""), MyMarker.class);
                arrayMarker.add(mm);
                listMarker.add(mainActivity.mMap.addMarker(new MarkerOptions().position(mm.latLng).title(mm.title).zIndex(mm.zindex)));
            }

        }
    }
    public void save(MainActivity mainActivity,MarkerOptions markerOptions)
    {

        SharedPreferences.Editor  editor = mainActivity.sharedPreferences.edit();
        MyMarker myMaker = new MyMarker(markerOptions.getPosition(),markerOptions.getTitle(),markerOptions.getSnippet(),markerOptions.getZIndex());
        arrayMarker.add(myMaker);
       // mainActivity.mMap.addMarker(markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        Toast toast = Toast.makeText(mainActivity,"Saved",Toast.LENGTH_LONG);
        toast.show();
        count++;
        for (int i =0 ; i < count;i++)
        editor.putString("marker"+String.valueOf(i),gson.toJson(arrayMarker.get(i)));

        editor.putInt("count",count);
        editor.apply();
        mainActivity.markerManager = new MarkerManager(mainActivity);

    }
    public void delete(MainActivity mainActivity)
    {
        SharedPreferences.Editor  editor = mainActivity.sharedPreferences.edit();
        arrayMarker.remove((int)currentMyMarker.zindex);
        int val = (int) mainActivity.choosingMarker.getZIndex();
        if(val>=0 && val<count){
        listMarker.get(val).remove();
        count--;
        refresh();
        for (int i =0 ; i < count;i++)
            editor.putString("marker"+String.valueOf(i),gson.toJson(arrayMarker.get(i)));

        editor.putInt("count",count);
        editor.apply();
        Toast toast = Toast.makeText(mainActivity,"Deleted",Toast.LENGTH_LONG);
        toast.show();}
    }
    public void refresh()
    {
        for (int i=0;i < count;i++)
            arrayMarker.get(i).zindex = (float)i;
    }
    public void setCurrentMyMarker(MyMarker myMarker)
    {
        this.currentMyMarker = myMarker;
    }


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }



    public MyMarker getCurrentMyMarker() {
        return currentMyMarker;
    }
}
