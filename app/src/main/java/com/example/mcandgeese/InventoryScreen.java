package com.example.mcandgeese;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

public class InventoryScreen extends AppCompatActivity {
    ListView simpleList;
    ImageView inventoryImage;
    TextView inventoryText;
    int listIdx;

    // height in px to be scrolled each time
    final int heightToScroll = 50;
    Item[] items = new Item[]{Item.getGooseFeather(), Item.getMetalPiece(), Item.getAmoeba(),
            Item.getLionTooth(), Item.getPinkBracelet(), Item.getWoodPiece()};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_screen);
        simpleList = (ListView) findViewById(R.id.simpleListView);
        simpleList.setNestedScrollingEnabled(true);
        inventoryImage = (ImageView) findViewById(R.id.inventoryImage);
        inventoryText = (TextView) findViewById(R.id.inventoryText);
        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), items);
        simpleList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        simpleList.setAdapter(customAdapter);
        listIdx = 0;

        if (items.length > 0){
            simpleList.setItemChecked(listIdx, true);
            simpleList.setSelection(listIdx);
            inventoryImage.setImageResource(items[listIdx].getImageId());
            inventoryText.setText(items[listIdx].getDescription());
        }
    }

    public void moveBack(View view){
        this.finish();
    }

    public void moveListCursorDown(View cursorDown){
        listIdx++;
        listIdx = Math.min(listIdx, items.length-1);
        simpleList.setItemChecked(listIdx, true);
        simpleList.setSelection(listIdx);
        inventoryImage.setImageResource(items[listIdx].getImageId());
        inventoryText.setText(items[listIdx].getDescription());
        // scrolling the list down a bit to follow cursor
        if (listIdx != items.length-1) {
            // simpleList.scrollBy(0, heightToScroll);
        }
    }

    public void moveListCursorUp(View cursorUp){
        listIdx--;
        listIdx = Math.max(listIdx, 0);
        simpleList.setItemChecked(listIdx, true);
        simpleList.setSelection(listIdx);
        inventoryImage.setImageResource(items[listIdx].getImageId());
        inventoryText.setText(items[listIdx].getDescription());
        // scrolling the list up a bit to follow cursor
        if (listIdx != 0) {
            // simpleList.scrollBy(0, -heightToScroll);
        }
    }
}
