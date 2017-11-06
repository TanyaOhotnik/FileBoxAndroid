package com.java.kv_30.kvapp.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.java.kv_30.kvapp.R;
import com.java.kv_30.kvapp.fragments.AccountFragment;
import com.java.kv_30.kvapp.fragments.DownloadFileFragment;
import com.java.kv_30.kvapp.fragments.ListOfFilesFragment;
import com.java.kv_30.kvapp.fragments.ShareFileFragment;
import com.java.kv_30.kvapp.fragments.TechnicalSupportFragment;
import com.java.kv_30.kvapp.fragments.UploadFileFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final int LAYOUT = R.layout.activity_main;
    private static final String TAG = "MainActivity";

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);
        initToolbar();
        initNavigationDrawer();
        initFragment(ListOfFilesFragment.getInstance());
//        initFloatingActionButton();
    }

    private void initFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        //if fragment already loaded in container
//        if(fm.findFragmentById(fragment.getId())==null) return;

            fm.beginTransaction().replace(R.id.fragment_container, fragment).commit();
            Log.d(TAG, "loaded");

    }

    private void initNavigationDrawer() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, mToolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
    }

//    private void initFloatingActionButton() {
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
//    }


//   menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()) {
            case R.id.nav_account:
               fragment = AccountFragment.getInstance();
                Log.d(TAG,"accountFragmentLoaded");
                break;
            case R.id.nav_download_file_by_link:
                fragment = DownloadFileFragment.getInstance();
                break;
            case R.id.nav_list_of_files:
                fragment = ListOfFilesFragment.getInstance();
                break;
            case R.id.nav_share_file:
                fragment = ShareFileFragment.getInstance();
                break;
            case R.id.nav_technical_support:
                fragment = TechnicalSupportFragment.getInstance();
                break;
            case R.id.nav_upload_file:
                fragment = UploadFileFragment.getInstance();
                break;
        }
        if(fragment!=null)
            initFragment(fragment);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }
}
