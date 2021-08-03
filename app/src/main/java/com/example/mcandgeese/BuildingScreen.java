package com.example.mcandgeese;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.util.HashMap;

public class BuildingScreen extends AppCompatActivity {
    ImageView buildingImage;
    public String buildingId;
    Button nearbyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building_screen);

        buildingId = getIntent().getStringExtra("BUILDING_ID");
        buildingImage = (ImageView) findViewById(R.id.secondImage);
        nearbyButton = (Button) findViewById(R.id.nearby);
        int imageResource = getResources().getIdentifier("@drawable/campusmap", null, this.getPackageName());
        switch (buildingId) {
            case ("E5"):
                imageResource = getResources().getIdentifier("@drawable/e5", null, this.getPackageName());
                nearbyButton.setText("E7");
                break;
            case ("E7"):
                imageResource = getResources().getIdentifier("@drawable/e7", null, this.getPackageName());
                nearbyButton.setText("Plaza");
                break;
            case ("SLC"):
                imageResource = getResources().getIdentifier("@drawable/slc", null, this.getPackageName());
                nearbyButton.setText("E5");
                break;
            case ("DP"):
                imageResource = getResources().getIdentifier("@drawable/dp", null, this.getPackageName());
                nearbyButton.setText("QNC");
                break;
            case("Plaza"):
                imageResource = getResources().getIdentifier("@drawable/plaza", null, this.getPackageName());
                Button eat = (Button) findViewById(R.id.FIGHT);
                eat.setText("Eat/Drink");
                Button goback = (Button) findViewById(R.id.RUN);
                goback.setText("Return");
                nearbyButton.setText("DP");
                break;
            case("QNC"):
                imageResource = getResources().getIdentifier("@drawable/quantum", null, this.getPackageName());
                nearbyButton.setText("SLC");
                break;
        }
        buildingImage.setImageResource(imageResource);

        final TypeWriter tw = (TypeWriter) findViewById(R.id.buildingTypeWriter);
        tw.setText("");
        tw.setCharacterDelay(70);
        tw.animateText(getStoryLine(buildingId));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onPause() {
        // Save the user's current game state
        SharedPreferences sharedPreferences = getSharedPreferences(MainActivity.sharedPreferencesKey,MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        GlobalVariables globalVariables = (GlobalVariables) this.getApplication();
        boolean gameStarted = globalVariables.getGameStarted();
        if (gameStarted) {
            try {
                String serializedGame = globalVariables.toString("");
                edit.putString("game", serializedGame);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        edit.commit();
        super.onPause();
    }

    public void goToBuildingScreen(View view) {
        Intent intent = new Intent(BuildingScreen.this, transition_screen.class);
        switch (buildingId) {
            case ("E5"):
                intent.putExtra("BUILDING_ID", "E7");
                break;
            case ("E7"):
                intent.putExtra("BUILDING_ID", "Plaza");
                break;
            case ("DP"):
                intent.putExtra("BUILDING_ID", "QNC");
                break;
            case ("SLC"):
                intent.putExtra("BUILDING_ID", "E5");
                break;
            case ("Plaza"):
                intent.putExtra("BUILDING_ID", "DP");
                break;
            case ("QNC"):
                intent.putExtra("BUILDING_ID", "SLC");
                break;
        }
        startActivity(intent);
    }

    public void testInventory(View view){
        GlobalVariables globalVariables = (GlobalVariables) this.getApplication();
        for (int i=1; i<=5; i++) {
            globalVariables.defeatMonster(i);
        }
    }

    public void goToMonsterTransitionScreen(View view) {
        String buildingId = getIntent().getStringExtra("BUILDING_ID");
        if (buildingId.equals("Plaza")) {
            ((GlobalVariables) this.getApplication()).setCurrentHealth(100);
            ((GlobalVariables) this.getApplication()).setCurrentEnergy(100);
            Intent intent = new Intent(BuildingScreen.this, transition_screen.class);
            intent.putExtra("BUILDING_ID", "Food");
            startActivity(intent);
            return;
        }

        Intent intent = new Intent(BuildingScreen.this, MonsterTransitionScreen.class);
        HashMap<Integer, Integer> buildingToMonster = ((GlobalVariables) this.getApplication()).getBuildingToMonster();

        int buildingID = Monster.getBuildingFromString(buildingId);
        int monsterID;

        if (buildingToMonster.get(buildingID) != null) {
            monsterID = buildingToMonster.get(buildingID);
        } else {
            monsterID = -1;
        }

        Monster generatedMonster = Monster.getMonsterFromID(monsterID);
        intent.putExtra("MONSTER_HEALTH", generatedMonster.getMonsterHealth());
        intent.putExtra("MONSTER_HIT_POINTS", generatedMonster.getMonsterHitPoints());
        intent.putExtra("MONSTER_NAME", generatedMonster.getMonsterName());
        intent.putExtra("BUILDING_ID", buildingID);
        intent.putExtra("MONSTER_ID", monsterID);
        startActivity(intent);
    }

    public void goToMapScreen(View view) {
        Intent intent = new Intent(BuildingScreen.this, MapScreen.class);
        startActivity(intent);
    }

    public void goToInventoryScreen(View view) {
        Intent intent = new Intent(BuildingScreen.this, InventoryScreen.class);
        intent.putExtra("BUILDING_ID", buildingId);
        startActivity(intent);
    }

    public String getStoryLine(String buildingId) {
        // use hashmap to store building activities
        HashMap<String, String> buildingActivity = new HashMap<>();
        buildingActivity.put("E5", "take linkedin pictures");
        buildingActivity.put("E7", "play with the light up pucks");
        buildingActivity.put("SLC", "find our lost items at turnkey");
        buildingActivity.put("DP", "enjoy the christmas tree in the winter");
        buildingActivity.put("Plaza", "spend all our money on bubble tea");
        buildingActivity.put("QNC","study next to the big windows");

        StringBuilder sb = new StringBuilder();

        // TODO check if building is cleared and switch intro
        sb.append("Looks like the geese have invaded " + buildingId + "! ");
        sb.append("Please clear this building so that we can " + buildingActivity.get(buildingId) + " again! \n");

        sb.append("Please choose an action!");
        return sb.toString();
    }
}