package com.example.dell.projectdemo5;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback ,View.OnClickListener {
    protected GoogleMap mmap;
    DataUniversity data;
    MarkerManager markerManager;
    Button btnTest;
    private final static LatLng hust = new LatLng(21.00619245,105.84313945);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map); // them map
        assert mapFragment != null;
        Bundle bundle = this.getIntent().getBundleExtra("data");
        ArrayList<University> list = bundle.getParcelableArrayList("uni");
        data = new DataUniversity(list);
        markerManager = new MarkerManager();
        markerManager.getList(data.getUniversityList());
        btnTest = findViewById(R.id.btnTest);
        btnTest.setOnClickListener(this);
        mapFragment.getMapAsync(this);
    }
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onMapReady(GoogleMap googleMap)   {
        mmap = googleMap;
        markerManager.addToMap(mmap);
        mmap.moveCamera(CameraUpdateFactory.newLatLngZoom(hust,15));
    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnTest:
            {
                markerManager.removeMarker();
            }
        }
    }
}
