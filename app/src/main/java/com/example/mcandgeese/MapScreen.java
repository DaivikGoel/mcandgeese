package com.example.mcandgeese;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.HashMap;

public class MapScreen extends AppCompatActivity {
    ImageView firstImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_screen);
        firstImage = (ImageView) findViewById(R.id.firstImage);
        HashMap<Integer, Integer> buildingToMonster = ((GlobalVariables) this.getApplication()).getBuildingToMonster();

        Button E5 = (Button) findViewById(R.id.E5);
        Button E7 = (Button) findViewById(R.id.E7);
        Button DP = (Button) findViewById(R.id.DP);
        Button SLC = (Button) findViewById(R.id.SLC);
        Button QNC = (Button) findViewById(R.id.QNC);

        int unicode = 0x1F479;
        String monster = "/" + getEmojiByUnicode(unicode);

        if(buildingToMonster.get(1) != null){

            E5.setText("E5" + monster);
        }

        if(buildingToMonster.get(2) != null){

            E7.setText("E7" + monster);
        }

        if(buildingToMonster.get(3) != null){

            SLC.setText("SLC" + monster);
        }

        if(buildingToMonster.get(4) != null){

            DP.setText("DP" + monster);
        }

        if(buildingToMonster.get(5) != null){

            QNC.setText("QNC" + monster);
        }


        int imageResource = getResources().getIdentifier("@drawable/campusmap", null, this.getPackageName());
        firstImage.setImageResource(imageResource);
    }

    public void goToBuildingScreen(View view) {
        Intent intent = new Intent(MapScreen.this, transition_screen.class);
        switch (view.getId()) {
            case (R.id.E5):
                intent.putExtra("BUILDING_ID", "E5");
                break;
            case (R.id.E7):
                intent.putExtra("BUILDING_ID", "E7");
                break;
            case (R.id.DP):
                intent.putExtra("BUILDING_ID", "DP");
                break;
            case (R.id.SLC):
                intent.putExtra("BUILDING_ID", "SLC");
                break;
            case (R.id.Plaza):
                intent.putExtra("BUILDING_ID", "Plaza");
                break;
            case (R.id.QNC):
                intent.putExtra("BUILDING_ID", "QNC");
                break;
        }
        startActivity(intent);
    }

    public void goToFreeRoamScreen(View view) {
        Intent intent = new Intent(MapScreen.this, ScrollableMap.class);
        startActivity(intent);
    }

    public void goToFinalEventsScreen(View view) {
        boolean isReady = ((GlobalVariables) this.getApplication()).noMonstersRemain();

        // comment out for testing
        /*
        if (!isReady) {
            Intent intent = new Intent(MapScreen.this, NotReadyScreen.class);
            startActivity(intent);
            return;
        }
        */

        Intent intent = new Intent(MapScreen.this, MonsterTransitionScreen.class);
        intent.putExtra("FINAL_EVENT", 1);
        startActivity(intent);

    }
    public String getEmojiByUnicode(int unicode){
        return new String(Character.toChars(unicode));
    }

}