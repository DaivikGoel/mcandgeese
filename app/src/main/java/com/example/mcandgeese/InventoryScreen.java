package com.example.mcandgeese;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class InventoryScreen extends AppCompatActivity {
    ListView simpleList;
    Item[] items = new Item[]{Item.getGooseFeather(), Item.getMetalPiece(), Item.getAmoeba(),
            Item.getLionTooth(), Item.getPinkBracelet(), Item.getWoodPiece()};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_screen);
        simpleList = (ListView) findViewById(R.id.simpleListView);
        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), items);
        simpleList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        simpleList.setAdapter(customAdapter);

        if (items.length > 0){
            simpleList.setItemChecked(0, true);
        }
    }
}
