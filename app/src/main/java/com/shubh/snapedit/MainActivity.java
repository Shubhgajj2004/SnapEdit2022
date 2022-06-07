package com.shubh.snapedit;


import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.splashscreen.SplashScreen;
import androidx.recyclerview.widget.GridLayoutManager;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;


import com.github.dhaval2404.imagepicker.ImagePicker;
import com.shubh.snapedit.Adapter.List_adapter;
import com.shubh.snapedit.Model.image_list_model;
import com.shubh.snapedit.databinding.ActivityMainBinding;

import java.io.File;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {


    // Declaration
    ActivityMainBinding binding;
    ArrayList<image_list_model> list = new ArrayList<>();
    List_adapter adapter;
    public static Uri imgUri;

    private final ActivityResultLauncher<String> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), result -> {
        if (result) {
            getImages();
        }
    });



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Handle the splash screen transition.
        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        //To Pick image with some editing features
        binding.EditButton.setOnClickListener(view -> ImagePicker.Companion.with(MainActivity.this)
                .crop()                    //Crop image(Optional), Check Customization for more option
                .start());


        binding.Recycler.setLayoutManager(new GridLayoutManager(MainActivity.this, 3));


        //If not allowed, check permissions, then make a pop up to first let.
        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            activityResultLauncher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        } else if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            activityResultLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE);
        } else {
            getImages();
        }


    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        try {
            imgUri = data.getData();
            if (!imgUri.equals(""))
                startActivity(new Intent(MainActivity.this, resultActivity.class));

        } catch (Exception e) {
        }
    }


    //Function to Feed saved images to recyclerview
    private void getImages() {
        list.clear();

        String filepath = "/storage/emulated/0/SnapEdit"; //Location of saved images
        File file = new File(filepath);
        File[] files = file.listFiles();
        if (files != null) {
            for (File file1 : files) {
                if (file1.getPath().endsWith(".png") || file1.getPath().endsWith(".jpg")) {
                    list.add(new image_list_model(file1.getPath(), file1.getName()));

                }
            }

        }


        adapter = new List_adapter(MainActivity.this, list);
        binding.Recycler.setAdapter(adapter);


    }




}