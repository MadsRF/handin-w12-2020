package com.example.imagedemowithrefactor.controller;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.imagedemowithrefactor.MainActivity;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class ImageController {

    private MainActivity mainActivity;

    public Bitmap bitmap;

    public ImageController(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void handleImageReturn(int requestCode, @Nullable Intent intent) {
        if (requestCode == 0) {
            //Get the url for the image
            Uri uri = intent.getData();
            try {
                //Create an inputstream to read the file
                InputStream is = mainActivity.getContentResolver().openInputStream(uri);
                //Make Bitmap from stream
                bitmap = BitmapFactory.decodeStream(is);
                //Set bitmap to imageView
                mainActivity.imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (requestCode == 1) { //it's the camera
            //Get the data with intent
            Bitmap bitmap = (Bitmap) intent.getExtras().get("data");
            //Assign bitmap to imageView
            mainActivity.imageView.setImageBitmap(bitmap);
        }

    }

}