package com.example.dell.projectdemo5;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
public class UniversityDialog extends DialogFragment {
    TextView txtTenTruong;
    ImageView imgStar;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.university,container);
        txtTenTruong = view.findViewById(R.id.uniName);
        imgStar = view.findViewById(R.id.imgstar);
        getDialog().setTitle("        THÔNG TIN TRƯỜNG");
        final MainActivity main  = (MainActivity) getActivity();
        assert main != null;
        String ten = main.currentUni.getName();
        txtTenTruong.setText(ten);
        int resource = 0;
        switch (main.currentUni.getQuality())
        {
            case 0 :
            {
                resource = R.drawable.onestar;
                break;
            }
            case 1 :
            {
                resource = R.drawable.twostar;
                break;
            }
            case 2 :
            {
                resource = R.drawable.threestar;
                break;
            }
            case 3:
            {
                resource = R.drawable.fourstar;
                break;
            }
            case 4 :
            {
                resource = R.drawable.fivestar;
                break;
            }
        }
        imgStar.setImageResource(resource);
        return view;
    }

    public UniversityDialog getInstance()
    {
        return this;
    }
}
