package com.example.dell.projectdemo4;

import com.google.android.gms.maps.model.LatLng;

public class MyMarker {
    LatLng latLng;
    String title,snippet;
    float zindex;

    public MyMarker(LatLng latLng, String title, String snippet,float zindex) {
        this.latLng = latLng;
        this.title = title;
        this.snippet = snippet;
        this.zindex = zindex;
    }

}
