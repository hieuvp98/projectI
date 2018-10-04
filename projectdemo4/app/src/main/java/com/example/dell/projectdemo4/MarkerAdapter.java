package com.example.dell.projectdemo4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MarkerAdapter extends ArrayAdapter<MyMarker> {
    private Context context;
    private List<MyMarker> myMarkerList;

    public MarkerAdapter(Context context,  ArrayList<MyMarker> myMarkerList) {
        super(context, R.layout.mylistview, myMarkerList);
        this.context = context;
        this.myMarkerList = myMarkerList;
    }
    public static class ViewHolder
    {
        ImageView imageView;
        TextView txtTitle;
        TextView txtSnippet;
    }
    private int lastPosition = -1;
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        MyMarker myMarker1 = getItem(position);
        ViewHolder viewHolder;

        if(convertView == null)
        {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.mylistview,parent,false);
            viewHolder.txtTitle = convertView.findViewById(R.id.txtTitle);
            viewHolder.txtSnippet = convertView.findViewById(R.id.txtsnippet);
            viewHolder.imageView = convertView.findViewById(R.id.imgMarker);

            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
            }
            lastPosition = position;
            viewHolder.txtTitle.setText(myMarker1.title);
            viewHolder.txtSnippet.setText(myMarker1.snippet);
            viewHolder.imageView.setImageResource(R.drawable.marker);
        return convertView;
    }
}

