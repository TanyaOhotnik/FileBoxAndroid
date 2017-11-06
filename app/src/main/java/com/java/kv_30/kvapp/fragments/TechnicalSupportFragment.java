package com.java.kv_30.kvapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.java.kv_30.kvapp.R;
import com.java.kv_30.kvapp.dto.Resource;

import java.util.List;

/**
 * Created by TanyaOhotnik on 04.11.2017.
 */

public class TechnicalSupportFragment extends Fragment {
    private static final int LAYOUT = R.layout.fragment_list_of_files;

    private static final String TAG = "TechnicalSuppFragment";

    public static Fragment getInstance(){
        TechnicalSupportFragment fragment = new TechnicalSupportFragment();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG,"loaded");
        View view = inflater.inflate(LAYOUT,container,false);

        LinearLayout layout = (LinearLayout)view.findViewById(R.id.linear_layout);
        TextView t = new TextView(getActivity());
        t.setText(TAG);
        layout.addView(t);

        return view;
    }

}
