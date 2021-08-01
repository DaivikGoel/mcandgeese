package com.example.mcandgeese;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Button;

import java.util.HashMap;

public class BuildingScreen extends AppCompatActivity {
    ImageView buildingImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building_screen);

        String buildingId = getIntent().getStringExtra("BUILDING_ID");
        buildingImage = (ImageView) findViewById(R.id.secondImage);
        int imageResource = getResources().getIdentifier("@drawable/campusmap", null, this.getPackageName());
        switch (buildingId) {
            case ("E5"):
                imageResource = getResources().getIdentifier("@drawable/e5", null, this.getPackageName());
                break;
            case ("E7"):
                imageResource = getResources().getIdentifier("@drawable/e7", null, this.getPackageName());
                break;
            case ("SLC"):
                imageResource = getResources().getIdentifier("@drawable/slc", null, this.getPackageName());
                break;
            case ("DP"):
                imageResource = getResources().getIdentifier("@drawable/pac", null, this.getPackageName());
                break;
            case("Plaza"):
                imageResource = getResources().getIdentifier("@drawable/plaza", null, this.getPackageName());
                Button eat = (Button) findViewById(R.id.FIGHT);
                eat.setText("Eat/Drink");
                Button goback = (Button) findViewById(R.id.RUN);
                goback.setText("Return");
                break;
            case("QNC"):
                imageResource = getResources().getIdentifier("@drawable/quantum", null, this.getPackageName());
                break;
        }
        buildingImage.setImageResource(imageResource);

        final TypeWriter tw = (TypeWriter) findViewById(R.id.buildingTypeWriter);
        tw.setText("");
        tw.setCharacterDelay(70);
        tw.animateText(getStoryLine(buildingId));
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
        intent.putExtra("MONSTER_ID", generatedMonster.getMonsterID());
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