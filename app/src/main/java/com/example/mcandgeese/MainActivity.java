package com.example.mcandgeese;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;

import android.content.Intent;
import android.widget.Button;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {
    private static final String sharedPreferencesKey = "MCANDGEESEPREF";
    Button continueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((GlobalVariables) this.getApplication()).initializeVariables();
        setContentView(R.layout.activity_main);
        continueButton = (Button) findViewById(R.id.continueGame);
        SharedPreferences sharedPreferences = getSharedPreferences(sharedPreferencesKey,MODE_PRIVATE);
        String stringifiedGame = sharedPreferences.getString("game", "");
        if (stringifiedGame == ""){
            continueButton.setVisibility(View.GONE);
        } else {
            continueButton.setVisibility(View.VISIBLE);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onPause(){
        super.onPause();
        // Storing data into SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences(sharedPreferencesKey,MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        GlobalVariables globalVariables = (GlobalVariables) this.getApplication();
        boolean gameStarted = globalVariables.getGameStarted();
        if (gameStarted) {
            try {
                String stringifiedGame = globalVariables.toString("");
                edit.putString("game", stringifiedGame);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        edit.commit();
    }

    public void goToMapScreen(View view) {
        Intent intent = new Intent(MainActivity.this, transition_screen.class);
        intent.putExtra("BUILDING_ID", "begin");
        GlobalVariables globalVariables = (GlobalVariables) this.getApplication();
        globalVariables.setGameStarted(true);
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void goToMapScreenContinue(View view) throws IOException, ClassNotFoundException {
        Intent intent = new Intent(MainActivity.this, transition_screen.class);
        intent.putExtra("BUILDING_ID", "begin");
        GlobalVariables globalVariables = (GlobalVariables) this.getApplication();
        SharedPreferences sharedPreferences = getSharedPreferences(sharedPreferencesKey,MODE_PRIVATE);
        globalVariables.fromString(sharedPreferences.getString("game", ""));
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