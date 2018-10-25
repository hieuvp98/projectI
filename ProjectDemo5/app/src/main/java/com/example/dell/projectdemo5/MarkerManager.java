package com.example.dell.projectdemo5;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MarkerManager {
    private List<MarkerOptions> markerOptionsList;
    private List<Marker> markerList;

    public MarkerManager() {
        markerOptionsList = new ArrayList<>();
        markerList = new ArrayList<>();
    }

    public void getList(ArrayList<University> list) {
        for (University u : list)
            markerOptionsList.add(u.toMarkerOptions());
    }

    public void addToMap(GoogleMap map) {
        for (MarkerOptions m : this.markerOptionsList)
            this.markerList.add(map.addMarker(m));
    }
    public void removeMarker()
    {
        for ( Marker m : markerList)
            m.remove();
    }
    public List<MarkerOptions> getMarkerOptionsList() {
        return markerOptionsList;
    }

    public void setMarkerOptionsList(List<MarkerOptions> markerOptionsList) {
        this.markerOptionsList = markerOptionsList;
    }

    public List<Marker> getMarkerList() {
        return markerList;
    }

    public void setMarkerList(List<Marker> markerList) {
        this.markerList = markerList;
    }
}
