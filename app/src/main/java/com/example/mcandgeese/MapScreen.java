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
        Intent intent = new Intent(MapScreen.this, BuildingScreen.class);
        switch (view.getId()) {
            case (R.id.E5):
                intent.putExtra("BUILDING_ID", "E5");
                break;
            case (R.id.E7):
                intent.putExtra("BUILDING_ID", "E7");
                break;
            case (R.id.PAC):
                intent.putExtra("BUILDING_ID", "PAC");
                break;
            case (R.id.SLC):
                intent.putExtra("BUILDING_ID", "SLC");
                break;
            case (R.id.Plaza):
                intent.putExtra("BUILDING_ID", "SLC");
                break;
            case (R.id.RCH):
                intent.putExtra("BUILDING_ID", "SLC");
                break;
        }
        startActivity(intent);
    }

}