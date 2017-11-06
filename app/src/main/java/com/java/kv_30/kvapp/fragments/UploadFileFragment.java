package com.java.kv_30.kvapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.java.kv_30.kvapp.R;

/**
 * Created by TanyaOhotnik on 04.11.2017.
 */

public class UploadFileFragment extends Fragment {
    private static final int LAYOUT = R.layout.fragment_list_of_files;

    private static final String TAG = "UploadFileFragment";

    public static Fragment getInstance(){
        UploadFileFragment fragment = new UploadFileFragment();
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
