package com.example.mcandgeese;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;

import android.content.Intent;


public class MainActivity extends AppCompatActivity {
    private static final String sharedPreferencesKey = "MCANDGEESEPREF";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((GlobalVariables) this.getApplication()).initializeVariables();
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onPause(){
        super.onPause();
        // Storing data into SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences(sharedPreferencesKey,MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        GlobalVariables globalVariables = (GlobalVariables) this.getApplication();
        edit.putInt("health", globalVariables.getCurrentHealth());
        edit.commit();
    }

    @Override
    protected void onResume(){
        super.onResume();
        // Retrieving data from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences(sharedPreferencesKey,MODE_PRIVATE);
        GlobalVariables globalVariables = (GlobalVariables) this.getApplication();
        globalVariables.setCurrentHealth(sharedPreferences.getInt("health",0));
    }

    public void goToMapScreen(View view) {
        Intent intent = new Intent(MainActivity.this, transition_screen.class);
        intent.putExtra("BUILDING_ID", "begin");
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