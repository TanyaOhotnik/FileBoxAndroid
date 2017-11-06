package com.java.kv_30.kvapp.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.java.kv_30.kvapp.R;
import com.java.kv_30.kvapp.activity.Constants;
import com.java.kv_30.kvapp.activity.FileDetailsActivity;
import com.java.kv_30.kvapp.dao.ResourceDAO;
import com.java.kv_30.kvapp.dto.Resource;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by TanyaOhotnik on 04.11.2017.
 */

public class ListOfFilesFragment extends Fragment{
    private static final int LAYOUT = R.layout.fragment_list_of_files;
    private static final String TAG = "ListOfFilesFragment";
    private static final int TITLE = R.string.app_name;
    private RecyclerView mRecyclerView;
    private ResourceAdapter mAdapter;

    public static Fragment getInstance(){
        ListOfFilesFragment fragment = new ListOfFilesFragment();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG,"loaded");
        View view = inflater.inflate(LAYOUT,container,false);
        getActivity().setTitle(TITLE);

        initRecyclerView(view);
        return view;
    }

    private void initRecyclerView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.list_of_files_recycler_view);

        List<Resource> list =  new ResourceDAO().getAll();
        Log.d(TAG,list.size()+"");
        mAdapter = new ResourceAdapter(list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);

    }
    private class ResourceAdapter extends RecyclerView.Adapter<ResourceViewHolder>{
        private List<Resource> mResourcesList;

        public ResourceAdapter(List<Resource> list) {
            mResourcesList = list;
        }

        @Override
        public ResourceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.item_resource_recycler_view, parent, false);
            return new ResourceViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ResourceViewHolder holder, int position) {
            Resource resource = mResourcesList.get(position);
            holder.bindResource(resource);
            Log.d(TAG,resource.getName());
        }

        @Override
        public int getItemCount() {
            return mResourcesList.size();
        }
    }
    private class ResourceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        Resource mResource;
        TextView fileNameTextView;
        ImageButton deleteButton;
        ImageButton downloadButton;
        public ResourceViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            fileNameTextView = (TextView)itemView.findViewById(R.id.file_name_recycler_view);
            deleteButton = (ImageButton)itemView.findViewById(R.id.delete_file_button_recycler_view);
            downloadButton = (ImageButton)itemView.findViewById(R.id.download_file_button_recycler_view);
        }
        public void bindResource(Resource resource) {
            mResource = resource;
            fileNameTextView.setText(mResource.getName());
            setOnButtonClickListener();
        }

        private void setOnButtonClickListener() {
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            downloadButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), FileDetailsActivity.class);
            intent.putExtra(Constants.EXTRA_FILE_ID,mResource.getId());
//                    FileDetailsFragment.getIntent(getActivity(), mCashTransaction.getTransactionId());
            startActivity(intent);
//            Fragment fragment = FileDetailsFragment.getInstance(mResource.getId());
//            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
        }
    }
}