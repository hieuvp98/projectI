package com.example.dell.projectdemo4;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    protected GoogleMap mMap;
    protected SharedPreferences sharedPreferences ;
    private static final String TAG = "MARKER";
    private final static LatLng hust = new LatLng(21.00619245,105.84313945);
    protected Marker currentMarker;
    protected MarkerOptions currentMarkerOptions;
    private Banner banner;
    protected MarkerManager markerManager;
    protected Marker choosingMarker;
    protected Marker hustMarker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences(TAG, Context.MODE_PRIVATE);
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        banner = (Banner) fragmentManager.findFragmentById(R.id.banner);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        currentMarker = null;
        banner.truyen("21.00619245","105.84313945");
        banner.setClick(MainActivity.this);
        try {

        mapFragment.getMapAsync(this);}
        catch (Exception ex){}
    }
    public void goTo(LatLng latLng)
    {
        if (currentMarker != null) {
            currentMarker.remove();
            currentMarker = null;
        }
        if (currentMarker == null) {
            choosingMarker = null;
            currentMarkerOptions = new MarkerOptions()
                    .position(latLng).title("You pick here").zIndex(markerManager.getCount())
                    .snippet("Vĩ độ: " + latLng.latitude + " " + "Kinh độ: " + latLng.longitude)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
            currentMarker = mMap.addMarker(currentMarkerOptions);
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            String myLat = String.valueOf(latLng.latitude);
            String myLong = String.valueOf(latLng.longitude);
            banner.truyen(myLat,myLong);

        }
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        choosingMarker = null;
        banner.btnClear.setClickable(false);
        mMap = googleMap;
        markerManager = new MarkerManager(this); // khoi tao marker da luu
       hustMarker = mMap.addMarker(new MarkerOptions().position(hust)
                .title("Ha Noi University of Science and Technology").snippet("Số 1 Đại Cồ Việt, Hai Bà Trưng, Hà Nội"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hust,16));
        // Add a marker in Bach Khoa
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
               goTo(latLng);
            }
        });
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                choosingMarker = marker;
                if (choosingMarker != hustMarker)
                banner.btnClear.setClickable(true);
                markerManager.setCurrentMyMarker(new MyMarker(marker.getPosition(),marker.getTitle(),marker.getSnippet(),marker.getZIndex()));
                banner.truyen(String.valueOf(marker.getPosition().latitude),String.valueOf(marker.getPosition().longitude));
                return false;
            }
        });
    }
}
