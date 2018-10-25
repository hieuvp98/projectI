package com.example.dell.projectdemo5;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterUniversity extends ArrayAdapter<University> {
    private int resourceLayout;
    private Context mContext;
    private ArrayList<University> uniList;
    public AdapterUniversity(Context context, int resourceLayout, ArrayList<University> item)
    {
        super(context,resourceLayout,item);
        this.mContext = context;
        this.resourceLayout = resourceLayout;
        this.uniList = item;
    }
    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(mContext).inflate(R.layout.university_list,parent,false);
        TextView txtName = convertView.findViewById(R.id.list_uniname);
        University uni = uniList.get(position);
        txtName.setText(uni.getName());

        return convertView;
    }

}
