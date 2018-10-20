package com.example.dell.projectdemo5;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class University implements Parcelable {
    private String name;
    private String snippet;  // mo ta
    private double lat,lng; // vi do, kinh do
    private  int quality;    // 0 to 4

    public University() {
    }

    public University(String name, String snippet, double lat, double lng, int quality) {
        this.name = name;
        this.snippet = snippet;
        this.lat = lat;
        this.lng = lng;
        this.quality = quality;
    }
    public University(String buffer)
    {
        int i = 0;
        StringBuilder title = new StringBuilder();
        StringBuilder lat = new StringBuilder();
        StringBuilder lng = new StringBuilder();
        while (buffer.charAt(i) != '|')
            title.append(buffer.charAt(i++));
        ++i;
        int quality = Character.getNumericValue(buffer.charAt(i++));
        ++i;
        while (buffer.charAt(i) != '|')
            lat.append(buffer.charAt(i++));
        ++i;
        while (buffer.charAt(i) != '|')
            lng.append(buffer.charAt(i++));
        this.name = String.valueOf(title);
        this.quality = quality;
        this.lat = Double.valueOf(String.valueOf(lat));
        this.lng = Double.valueOf(String.valueOf(lng));
        this.snippet = "";
    }

    protected University(Parcel in) {
        name = in.readString();
        snippet = in.readString();
        lat = in.readDouble();
        lng = in.readDouble();
        quality = in.readInt();
    }

    public static final Creator<University> CREATOR = new Creator<University>() {
        @Override
        public University createFromParcel(Parcel in) {
            return new University(in);
        }

        @Override
        public University[] newArray(int size) {
            return new University[size];
        }
    };

    @Override
    public String toString()
    {
        return this.name+'\n'+String.valueOf(lat)+" - "+String.valueOf(lng)+'\n'+String.valueOf(quality);
    }
    public MarkerOptions toMarkerOptions()
    {
        MarkerOptions m = new MarkerOptions();
        m.title(this.name);
        m.position(new LatLng(this.lat,this.lng));
        switch (this.quality)
        {
            case 0: {
                m.icon(BitmapDescriptorFactory.fromResource(R.drawable.one));
                break;
            }
            case 1: {
                m.icon(BitmapDescriptorFactory.fromResource(R.drawable.two));
                break;
            }
            case 2: {
                m.icon(BitmapDescriptorFactory.fromResource(R.drawable.three));
                break;
            }
            case 3: {
                m.icon(BitmapDescriptorFactory.fromResource(R.drawable.four));
                break;
            }
            case 4: {
                m.icon(BitmapDescriptorFactory.fromResource(R.drawable.five));
                break;
            }
        }
        return m;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(snippet);
        dest.writeDouble(lat);
        dest.writeDouble(lng);
        dest.writeInt(quality);
    }
}
