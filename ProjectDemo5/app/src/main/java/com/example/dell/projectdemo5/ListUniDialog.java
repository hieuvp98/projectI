package com.example.dell.projectdemo5;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.gms.maps.CameraUpdateFactory;

public class ListUniDialog extends DialogFragment {
    ListView listUni;
    Button btnBackList;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    private DialogFragment getInstance()
    {
        return this;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_university_dialog,container);
        listUni = view.findViewById(R.id.list_university);
        btnBackList = view.findViewById(R.id.btn_back_list);
        getDialog().setTitle("         DANH SÁCH TRƯỜNG");
        setUp();
        return view;
    }
    public void setUp()
    {
        final MainActivity mainActivity = (MainActivity) getContext();
        assert mainActivity != null;
        AdapterUniversity adapterUniversity = new AdapterUniversity(mainActivity.getApplicationContext(),R.layout.university_list,mainActivity.data.getUniversityList());
        listUni.setAdapter(adapterUniversity);
        listUni.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mainActivity.mmap.moveCamera(CameraUpdateFactory.
                        newLatLng(mainActivity.markerManager.getMarkerOptionsList().get(position).getPosition()));
                 getInstance().dismiss();
                 mainActivity.markerManager.getMarkerList().get(position).showInfoWindow();
            }
        });
        btnBackList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getInstance().dismiss();
            }
        });
    }
}
