package com.example.imagedemowithrefactor;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.imagedemowithrefactor.controller.ImageController;

import java.io.FileNotFoundException;
import java.io.InputStream;

import static android.graphics.Bitmap.createScaledBitmap;

public class MainActivity extends AppCompatActivity {

    public ImageView imageView;
    private ImageController ic;
    Bitmap myBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
        ic = new ImageController(this);
    }

    public void photoRollBtnPressed(View view){
        Log.i("all", "clicked photo roll button");
        Intent intent = new Intent(Intent.ACTION_PICK); //
        intent.setType("image/*"); // choose what type is allowed
        startActivityForResult(intent, 0); // 0 = photo roll, 2 means camera
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != -1) return; // -1 = ok
        ic.handleImageReturn(requestCode, data);
    }

    public void cameraBtnPressed(View view){
        Log.i("all", "clicked camera button");
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 1); // 1 = camera
    }

    // Assign 12. HERE
    public void resizeBtnPressed(View view){
        Log.i("all", "clicked resize button");
        Bitmap resized = createScaledBitmap(ic.bitmap, 100, 100, true);
        imageView.setImageBitmap(resized);

    }

}
