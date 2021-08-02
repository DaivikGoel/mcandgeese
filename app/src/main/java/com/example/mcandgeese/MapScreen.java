package com.example.mcandgeese;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MapScreen extends AppCompatActivity {
    ImageView firstImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_screen);
        firstImage = (ImageView) findViewById(R.id.firstImage);


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
        boolean isReady = ((GlobalVariables) this.getApplication()).monstersRemain();

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

}