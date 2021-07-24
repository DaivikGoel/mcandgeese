package com.example.mcandgeese;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.Settings;
import android.view.View;

import android.content.Intent;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void goToMapScreen(View view) {
        Intent intent = new Intent(MainActivity.this, transition_screen.class);
        startActivity(intent);
    }
    public void goToBuildingScreen(View view) {
        Intent intent = new Intent(MainActivity.this, BuildingScreen.class);
        startActivity(intent);
    }
    public void goToBattleScreen(View view) {
        Intent intent = new Intent(MainActivity.this, BattleScreen.class);
        startActivity(intent);
    }

}