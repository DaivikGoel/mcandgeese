package com.example.mcandgeese;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;

import android.content.Intent;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {
    public static final String sharedPreferencesKey = "MCANDGEESEPREF";
    Button continueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GlobalVariables.getInstance().initializeVariables();
        setContentView(R.layout.activity_main);
        continueButton = (Button) findViewById(R.id.continueGame);
        SharedPreferences sharedPreferences = getSharedPreferences(sharedPreferencesKey,MODE_PRIVATE);
        String serializedGame = sharedPreferences.getString("game", "");
        if (serializedGame == ""){
            continueButton.setVisibility(View.GONE);
        } else {
            continueButton.setVisibility(View.VISIBLE);
        }
    }

//    @RequiresApi(api = Build.VERSION_CODES.O)
//    @Override
//    protected void onPause(){
//        // Storing data into SharedPreferences
//        SharedPreferences sharedPreferences = getSharedPreferences(sharedPreferencesKey,MODE_PRIVATE);
//        SharedPreferences.Editor edit = sharedPreferences.edit();
//        GlobalVariables globalVariables = GlobalVariables.getInstance();
//        boolean gameStarted = globalVariables.getGameStarted();
//        if (gameStarted) {
//            try {
//                String stringifiedGame = globalVariables.toString("");
//                edit.putString("game", stringifiedGame);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        edit.commit();
//        super.onPause();
//    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onPause() {
        // Save the user's current game state
        SharedPreferences sharedPreferences = getSharedPreferences(sharedPreferencesKey,MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        GlobalVariables globalVariables = GlobalVariables.getInstance();
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
        super.onPause();
    }

    public void goToMapScreen(View view) {
        Intent intent = new Intent(MainActivity.this, transition_screen.class);
        intent.putExtra("BUILDING_ID", "begin");
        SharedPreferences sharedPreferences = getSharedPreferences(sharedPreferencesKey,MODE_PRIVATE);
        String serializedGame = sharedPreferences.getString("game","");
        GlobalVariables globalVariables = GlobalVariables.getInstance();
        if (serializedGame != "") {
            AlertDialog new_game = new AlertDialog.Builder(this)
                    .setTitle("New Game")
                    .setMessage("Are you sure you want to start a new game? This will replace your " +
                            "previously saved game. \n In addition, starting a new game means you " +
                            "are aware that This game was intended to be enjoyed by University of Waterloo" +
                            " students and prospective students over the age of 16. The events and " +
                            "places described were created with humour for fun and not be an accurate " +
                            "representation of the university experience. Please click Agree to indicate" +
                            "that you are aware of these disclaimers, and that this game is not a " +
                            "completely accurate representation of UW")

                    // Continue with new game operation
                    .setPositiveButton("Agree", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            globalVariables.initializeVariables();
                            globalVariables.setGameStarted(true);
                            startActivity(intent);
                        }
                    })

                    // A null listener allows the button to dismiss the dialog and take no further action.
                    .setNegativeButton("Decline", null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        } else {
            AlertDialog new_game = new AlertDialog.Builder(this)
                    .setTitle("Start")
                    .setMessage("Note: This game was intended to be enjoyed by University of Waterloo" +
                            " students and prospective students over the age of 16. The events and " +
                            "places described were created with humour for fun and not be an accurate " +
                            "representation of the university experience. Please click Agree to indicate" +
                            "that you are aware of these disclaimers, and that this game is not a " +
                            "completely accurate representation of UW")

                    // Continue with new game operation
                    .setPositiveButton("Agree", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            globalVariables.initializeVariables();
                            globalVariables.setGameStarted(true);
                            startActivity(intent);
                        }
                    })

                    // A null listener allows the button to dismiss the dialog and take no further action.
                    .setNegativeButton("Decline", null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
            globalVariables.initializeVariables();
            globalVariables.setGameStarted(true);
            startActivity(intent);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void goToMapScreenContinue(View view) throws IOException, ClassNotFoundException {
        Intent intent = new Intent(MainActivity.this, transition_screen.class);
        intent.putExtra("BUILDING_ID", "begin");
        GlobalVariables globalVariables = GlobalVariables.getInstance();
        globalVariables.setGameStarted(true);
        SharedPreferences sharedPreferences = getSharedPreferences(sharedPreferencesKey,MODE_PRIVATE);
        String serializedGame = sharedPreferences.getString("game","");
        if (serializedGame != "") {
            globalVariables.fromString(serializedGame);
        }
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