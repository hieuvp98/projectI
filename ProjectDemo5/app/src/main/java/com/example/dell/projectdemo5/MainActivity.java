package com.example.dell.projectdemo5;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback ,View.OnClickListener,GoogleMap.OnMarkerClickListener {
    protected GoogleMap mmap;
    DataUniversity data;
    MarkerManager markerManager;
    Button btnList;
    University currentUni;
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
        btnList = findViewById(R.id.btnTest);
        btnList.setOnClickListener(this);
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
        mmap.setOnMarkerClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnTest:
            {
                showListUniDialog();
            }
        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        int val = (int) marker.getZIndex();
        currentUni = data.getUniversityList().get(val);
        showUniDialog();
        return false;
    }
    public void showUniDialog()
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        UniversityDialog universityDialog = new UniversityDialog();
        universityDialog.show(fragmentManager,"uni");
    }
    public void showListUniDialog()
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        ListUniDialog listUniDialog = new ListUniDialog();
        listUniDialog.show(fragmentManager,"list");
    }
}
