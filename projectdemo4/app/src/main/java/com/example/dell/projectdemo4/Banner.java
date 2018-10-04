package com.example.dell.projectdemo4;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Banner extends Fragment {
     EditText lat;
     EditText lng;
     Button btnGo,btnSave,btnClear;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.banner,container,false);
        lat = view.findViewById(R.id.edtLat);
        lng = view.findViewById(R.id.edtLng);
        btnGo = view.findViewById(R.id.btnGo);
        btnSave = view.findViewById(R.id.btnSave);
        btnClear = view.findViewById(R.id.btnclear);
        return view;
    }
    public void setClick(final MainActivity mainActivity)
    {
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mainActivity.currentMarker != null) {
                    mainActivity.currentMarker.remove();
                    mainActivity.currentMarker = null;
                }
                if (mainActivity.currentMarker == null) {
                    LatLng latLng = new LatLng(Double.parseDouble(lat.getText().toString()),Double.parseDouble(lng.getText().toString()));
                    mainActivity.currentMarkerOptions = new MarkerOptions().position(latLng).title("You pick here")
                            .zIndex(mainActivity.markerManager.getCount()).snippet
                            ("Vĩ độ: " + latLng.latitude + " " + "Kinh độ: " + latLng.longitude)
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
                    mainActivity.currentMarker = mainActivity.mMap.addMarker(mainActivity.currentMarkerOptions);
                    mainActivity.mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));}
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.markerManager.save(mainActivity,mainActivity.currentMarkerOptions);


            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mainActivity.choosingMarker == mainActivity.hustMarker)
                {
                    mainActivity.hustMarker.remove();
                }
                else {
                    mainActivity.markerManager.delete(mainActivity);
                }
            }
        });
    }
    public void setLat(String lat)
    {
        this.lat.setText(lat);
    }
    public void setLng(String lng)
    {
        this.lng.setText(lng);
    }
    public void truyen(String myLat,String myLong)
    {
        setLat(myLat);
        setLng(myLong);
    }
}
