package com.java.kv_30.kvapp.fragments;

import android.Manifest;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.OpenableColumns;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.java.kv_30.kvapp.BytesCipher;
import com.java.kv_30.kvapp.R;
import com.java.kv_30.kvapp.activity.Constants;
import com.java.kv_30.kvapp.dao.ResourceDAO;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by TanyaOhotnik on 04.11.2017.
 */

public class UploadFileFragment extends Fragment {
    private static final int LAYOUT = R.layout.fragment_upload_file;

    private static final String TAG = "UploadFileFragment";
    private TextView mTextView;
    private Uri mFileUri;

    public static Fragment getInstance() {
        UploadFileFragment fragment = new UploadFileFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "loaded");
        View view = inflater.inflate(LAYOUT, container, false);
        mTextView = (TextView) view.findViewById(R.id.file_name_upload);
        initOnButtonClickListener(view);

        return view;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {

        if (requestCode == Constants.CODE_REQUEST_WRITE_EXTERNAL_STORAGE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // permission was granted, yay! Do the
                // contacts-related task you need to do.

            } else {
                // permission denied, boo! Disable the
                // functionality that depends on this permission.
            }
            return;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) return;

        if (requestCode == Constants.CODE_FILE_SELECT) {
            mFileUri = data.getData();
            setFileNameInTextView(mFileUri);
            Log.d(TAG, "Selected file Uri: " + mFileUri.toString());
        }
    }

    private void setFileNameInTextView(Uri uri) {
        String result = uri.getPath();
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getActivity().getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        mTextView.setText(result);

    }

    private void initOnButtonClickListener(View view) {
        Button selectFileButton = (Button) view.findViewById(R.id.select_file_upload_button);
        Button uploadFileButton = (Button) view.findViewById(R.id.upload_file_button);
        selectFileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectFile();
            }
        });
        uploadFileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadFile();
            }
        });

    }

    private void selectFile() {
        //                check read permission
        if (checkPermission() == PackageManager.PERMISSION_DENIED)
            return;

//        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        Intent chooser = Intent.createChooser(intent, "Select app to choose file");
        try {
            startActivityForResult(chooser, Constants.CODE_FILE_SELECT);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getActivity(), "Please install a File Manager.", Toast.LENGTH_SHORT).show();
        }
    }

    private void uploadFile() {


//            DownloadManager.Request request = new DownloadManager.Request(uri);
//            request.setDestinationInExternalFilesDir(getActivity(),
//                    Environment.DIRECTORY_DOWNLOADS,"cats.txt");
        byte[] fileBytes = convertFileUriToBytes();

        fileBytes = BytesCipher.decryptBytesWithAES(fileBytes,"mysupersecretkey");
        //file loading at sd card
        FileOutputStream outputStream = null;
        try {

            File dir = new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOWNLOADS), "myFiles");
            if (!dir.mkdirs())
                Log.d(TAG, "Can't make dir");
            else
                Log.d(TAG, "Directory successfully created");
            File file = new File(dir, "cats.jpg");
            outputStream = new FileOutputStream(file);

//                FileOutputStream outputStream = new FileOutputStream(new File(getActivity().getExternalFilesDir( Environment.DIRECTORY_PICTURES),"cats.jpg"));
//                while ((read = (byte) inputStream.read()) != -1) {
                    outputStream.write(fileBytes);
//                    Log.d(TAG, "writebytes");
//                }
//            outputStream.write(23);
//            check getTotalSpace
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Can`t find selected file", Toast.LENGTH_SHORT).show();

        } finally {
            //up api level to use objects
            try {
                if (outputStream != null)
                    outputStream.close();
            } catch (Exception e) {
                Toast.makeText(getActivity(), "Can`t close file streams", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private byte[] convertFileUriToBytes() {
        InputStream inputStream = null;

        try {
            inputStream = getActivity().getContentResolver().openInputStream(mFileUri);
            ByteArrayOutputStream result = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }
            byte[] fileBytes = result.toByteArray();
//                new ResourceDAO().getKeyForResource();
            fileBytes = BytesCipher.encryptBytesWithAES(fileBytes, "mysupersecretkey");
            return fileBytes;
        } catch (FileNotFoundException ex) {
            Toast.makeText(getActivity(), "Can`t find selected file", Toast.LENGTH_SHORT).show();
            ex.printStackTrace();
        } catch (IOException ex) {
            Toast.makeText(getActivity(), "Can`t read selected file", Toast.LENGTH_SHORT).show();

        } finally {
            //up api level to use objects
            try {
                if (inputStream != null)
                    inputStream.close();
            } catch (Exception e) {
                Toast.makeText(getActivity(), "Can`t close file streams", Toast.LENGTH_SHORT).show();
            }
        }
        return null;
    }

    private int checkPermission() {
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    Constants.CODE_REQUEST_WRITE_EXTERNAL_STORAGE);
            Log.d(TAG, "ask for permissions");

        }
//        return PackageManager.PERMISSION_GRANTED;
        return ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }
}
