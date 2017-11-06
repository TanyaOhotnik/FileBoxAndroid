package com.java.kv_30.kvapp.fragments;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.java.kv_30.kvapp.R;

import java.util.Date;

/**
 * Created by TanyaOhotnik on 04.11.2017.
 */

public class UploadFileFragment extends Fragment {
    private static final int LAYOUT = R.layout.fragment_upload_file;
    private static final int FILE_SELECT_CODE = 0;
    private static final String TAG = "UploadFileFragment";

    public static Fragment getInstance(){
        UploadFileFragment fragment = new UploadFileFragment();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG,"loaded");
        View view = inflater.inflate(LAYOUT,container,false);

        initOnButtonClickListener(view);

        return view;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) return;
        if (requestCode == FILE_SELECT_CODE) {
            Uri uri = data.getData();
            Log.d(TAG, "File Uri: " + uri.toString());
            // Get the path
//            String path = FileUtils.getPath(this, uri);
//            Log.d(TAG, "File Path: " + path);
        }
    }
    private void initOnButtonClickListener(View view) {
        Button selectFileButton = (Button)view.findViewById(R.id.select_file_upload_button);
        selectFileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");
                intent.addCategory(Intent.CATEGORY_OPENABLE);

                try {
                    startActivityForResult(
                            Intent.createChooser(intent, "Select a File to Upload"),
                            FILE_SELECT_CODE);
                } catch (android.content.ActivityNotFoundException ex) {
                    // Potentially direct the user to the Market with a Dialog
                    Toast.makeText(getActivity(), "Please install a File Manager.",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
