package com.example.mcandgeese;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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


        Uri videPath= Uri.parse("android.resource://com.example.mcandgeese/raw/video");
        transitionVideo.setVideoURI(videPath);

        transitionVideo.start();
    }
    public void goToMapScreen(View view) {
        Intent intent = new Intent(transition_screen.this, MapScreen.class);
        startActivity(intent);
    }

}