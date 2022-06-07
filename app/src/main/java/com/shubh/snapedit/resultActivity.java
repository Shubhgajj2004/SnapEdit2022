package com.shubh.snapedit;

import static com.shubh.snapedit.MainActivity.imgUri;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.dsphotoeditor.sdk.activity.DsPhotoEditorActivity;
import com.dsphotoeditor.sdk.utils.DsPhotoEditorConstants;
import com.shubh.snapedit.databinding.ActivityResultBinding;

public class resultActivity extends AppCompatActivity {

    //Declaration
    ActivityResultBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityResultBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        //Intent to setting up the editor
            Intent dsPhotoEditorIntent = new Intent(resultActivity.this, DsPhotoEditorActivity.class);
            dsPhotoEditorIntent.setData(imgUri);   //set image url
            dsPhotoEditorIntent.putExtra(DsPhotoEditorConstants.DS_PHOTO_EDITOR_OUTPUT_DIRECTORY, "SnapEdit" );  //Location directory to save edited images.
            // dsPhotoEditorIntent.putExtra(DsPhotoEditorConstants.DS_MAIN_BACKGROUND_COLOR , Color.parseColor("#FFFFFF"));

        startActivityForResult(dsPhotoEditorIntent, 200);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {


            //after saving edited image, redirect to the Home screen
            Intent intent = new Intent(resultActivity.this , MainActivity.class);
            startActivity(intent);


        }

    }
}