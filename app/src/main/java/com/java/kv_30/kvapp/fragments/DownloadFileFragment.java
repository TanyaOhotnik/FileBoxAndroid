package com.java.kv_30.kvapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.java.kv_30.kvapp.R;

/**
 * Created by TanyaOhotnik on 04.11.2017.
 */

public class DownloadFileFragment extends Fragment {
    private static final int LAYOUT = R.layout.fragment_download_file;
    private static final String TAG = "DownloadFileFragment";
    private static final int TITLE = R.string.download_file_title;

    public static Fragment getInstance(){
        DownloadFileFragment fragment = new DownloadFileFragment();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG,"loaded");
        View view = inflater.inflate(LAYOUT,container,false);
        getActivity().setTitle(TITLE);
//        LinearLayout layout = (LinearLayout)view.findViewById(R.id.linear_layout);
//        TextView t = new TextView(getActivity());
//        t.setText(TAG);
//        layout.addView(t);
        setOnButtonClickListener(view);

        return view;
    }

    private void setOnButtonClickListener(View view) {
        Button downloadButton = (Button)view.findViewById(R.id.download_file_button);
        final EditText fileLink = (EditText)view.findViewById(R.id.file_link_edit_text);
        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),fileLink.getText(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
