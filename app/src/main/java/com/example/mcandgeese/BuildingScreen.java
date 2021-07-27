package com.example.mcandgeese;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Button;

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
    }

    public void goToBattleScreen(View view) {
        String buildingId = getIntent().getStringExtra("BUILDING_ID");
        switch(buildingId){
            case("Plaza"):
                ((GlobalVariables) this.getApplication()).setCurrentHealth(100);
                ((GlobalVariables) this.getApplication()).setCurrentEnergy(100);
                Intent intent = new Intent(BuildingScreen.this, transition_screen.class);
                intent.putExtra("BUILDING_ID", "Food");
                startActivity(intent);
                break;
            default:
                Intent normal = new Intent(BuildingScreen.this, BattleScreen.class);
                startActivity(normal);
                break;
        }
    }

    public void goToMapScreen(View view) {
        Intent intent = new Intent(BuildingScreen.this, MapScreen.class);
        startActivity(intent);
    }

    public String getStoryLine(String building) {
        // TODO: add typing effect to displayed text
        // use hashmap to store generic story lines and switch on whether if the building is cleared or not
        return "";
    }
}