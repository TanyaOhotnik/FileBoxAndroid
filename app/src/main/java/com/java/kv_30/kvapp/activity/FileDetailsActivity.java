package com.java.kv_30.kvapp.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.java.kv_30.kvapp.R;
import com.java.kv_30.kvapp.fragments.FileDetailsFragment;
import com.java.kv_30.kvapp.fragments.ListOfFilesFragment;

/**
 * Created by TanyaOhotnik on 06.11.2017.
 */

public class FileDetailsActivity extends AppCompatActivity {
    private static final int LAYOUT = R.layout.activity_details;
    private static final String TAG = "FileDetailsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);
        Long id = (Long)getIntent().getSerializableExtra(Constants.EXTRA_FILE_ID);
        initFragment(FileDetailsFragment.getInstance(id));
    }

    private void initFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.fragment_container, fragment).commit();
        Log.d(TAG, "loaded");

    }
}
