package com.example.mcandgeese;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.VideoView;
import android.content.Intent;
import android.view.View;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Arrays;

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
            case("Food"):
                pathtovid = returnrandomfood();
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
            case("begin"):
                goToMapScreen(view);
                break;
            case ("E5"):
                intent.putExtra("BUILDING_ID", "E5");
                startActivity(intent);
                break;
            case ("E7"):
                intent.putExtra("BUILDING_ID", "E7");
                startActivity(intent);
                break;
            case ("DP"):
                intent.putExtra("BUILDING_ID", "DP");
                startActivity(intent);
                break;
            case ("SLC"):
                intent.putExtra("BUILDING_ID", "SLC");
                startActivity(intent);
                break;
            case ("Food"):
            case ("Plaza"):
                intent.putExtra("BUILDING_ID", "Plaza");
                startActivity(intent);
                break;
            case ("QNC"):
                intent.putExtra("BUILDING_ID", "QNC");
                startActivity(intent);
                break;
        }

    }

    public String returnrandomfood(){
        String path = "android.resource://com.example.mcandgeese/raw/";
        List<String> foodchoices = new ArrayList<>(Arrays.asList("campuspizza", "lazeez", "panino", "sweetdreams"));

        Random rand = new Random();
        path = path + foodchoices.get(rand.nextInt(foodchoices.size()));
        return path;



    }

}