package com.java.kv_30.kvapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.java.kv_30.kvapp.R;
import com.java.kv_30.kvapp.activity.Constants;
import com.java.kv_30.kvapp.dao.ResourceDAO;
import com.java.kv_30.kvapp.dto.Resource;

/**
 * Created by TanyaOhotnik on 04.11.2017.
 */

public class FileDetailsFragment extends Fragment {
    private static final int LAYOUT = R.layout.fragment_file_details2;
    private static final String TAG = "FileDetailsFragment";


    private Resource resource;

    public static Fragment getInstance(Long id){
        FileDetailsFragment fragment = new FileDetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable(Constants.ARG_FILE_ID,id);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG,"loaded");
        resource = new ResourceDAO().getResourceById((Long)getArguments().getSerializable(Constants.ARG_FILE_ID));
        getActivity().setTitle(resource.getName());
        View view = inflater.inflate(LAYOUT,container,false);
        initResourceDetailsFields(view);
        return view;
    }

    private void initResourceDetailsFields(View view) {
        ((TextView)view.findViewById(R.id.file_name_value)).setText(resource.getName());
        ((TextView)view.findViewById(R.id.file_expiration_time_value)).setText(resource.getExpirationTime().toString());
        ((TextView)view.findViewById(R.id.file_size_value)).setText(String.valueOf(resource.getSize()));
        ((TextView)view.findViewById(R.id.file_permission_value)).setText(resource.getPermission().toString());
    }
}
