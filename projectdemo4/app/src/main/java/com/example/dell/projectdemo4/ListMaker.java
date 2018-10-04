package com.example.dell.projectdemo4;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

public class ListMaker extends DialogFragment {
    ListView listView;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mylistview,container,false);
        listView = view.findViewById(R.id.marker_list);
        return view;
    }
    public void setUp(final MainActivity mainActivity)
    {
        MarkerAdapter markerAdapter = new MarkerAdapter(mainActivity,mainActivity.markerManager.getArrayMarker());
        listView.setAdapter(markerAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mainActivity.goTo(mainActivity.markerManager.getArrayMarker().get(position).latLng);
            }
        });
    }
}
