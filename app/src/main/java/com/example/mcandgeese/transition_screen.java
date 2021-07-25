package com.example.mcandgeese;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.VideoView;
import android.content.Intent;
import android.view.View;
import android.net.Uri;
public class transition_screen extends AppCompatActivity {
    VideoView transitionVideo;
    private Uri videPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition_screen);
        transitionVideo = (VideoView) findViewById(R.id.transitionvideo);

        String videoId = getIntent().getStringExtra("BUILDING_ID");
        String pathtovid = "";
        switch (videoId) {
            case ("E5"):
                pathtovid = "android.resource://com.example.mcandgeese/raw/engineering5";
                break;
            case ("E7"):
                pathtovid ="android.resource://com.example.mcandgeese/raw/engineering7";
                break;
            case ("SLC"):
                pathtovid = "android.resource://com.example.mcandgeese/raw/studentlifecentre";
                break;
            case ("DP"):
                pathtovid ="android.resource://com.example.mcandgeese/raw/danaporter";
                break;
            case("Plaza"):
                pathtovid ="android.resource://com.example.mcandgeese/raw/plaza";
                break;
            case("QNC"):
                pathtovid = "android.resource://com.example.mcandgeese/raw/nanocentre";
                break;
            case("begin"):
                pathtovid ="android.resource://com.example.mcandgeese/raw/begin";
                break;

        }
        Uri videPath= Uri.parse(pathtovid);


        transitionVideo.setVideoURI(videPath);

        transitionVideo.start();
    }
    public void goToMapScreen(View view) {
        Intent intent = new Intent(transition_screen.this, MapScreen.class);
        startActivity(intent);
    }

    public void navigateToScreen(View view) {
        String imageId = getIntent().getStringExtra("BUILDING_ID");
        Intent intent = new Intent(transition_screen.this, BuildingScreen.class);
        switch (imageId) {
            case ("E5"):
                intent.putExtra("BUILDING_ID", "E5");
                break;
            case ("E7"):

                intent.putExtra("BUILDING_ID", "E7");
                break;
            case ("DP"):

                intent.putExtra("BUILDING_ID", "DP");
                break;
            case ("SLC"):
                intent.putExtra("BUILDING_ID", "SLC");
                break;
            case ("Plaza"):
                intent.putExtra("BUILDING_ID", "Plaza");
                break;
            case ("QNC"):
                intent.putExtra("BUILDING_ID", "QNC");
                break;
            case("begin"):
                goToMapScreen(view);
        }
        startActivity(intent);
    }

}