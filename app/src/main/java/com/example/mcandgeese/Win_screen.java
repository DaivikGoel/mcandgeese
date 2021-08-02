package com.example.mcandgeese;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Win_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win_screen);
    }

    public void goToMapScreen(View view) {
        Intent mapscreen = new Intent(Win_screen.this, MapScreen.class);
        startActivity(mapscreen);
    }
}