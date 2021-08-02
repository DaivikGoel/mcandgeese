package com.example.mcandgeese;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import java.util.ArrayList;
import java.util.List;

public class InventoryScreen extends AppCompatActivity {
    ListView simpleList;
    ImageView inventoryImage;
    TextView inventoryText;
    int listIdx;

    // height in px to be scrolled each time
    final int heightToScroll = 50;
    List<Item> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GlobalVariables globalVariables = (GlobalVariables) this.getApplication();
        items = globalVariables.getItems() == null ? new ArrayList<>() : globalVariables.getItems();

        setContentView(R.layout.activity_inventory_screen);
        simpleList = (ListView) findViewById(R.id.simpleListView);
        simpleList.setNestedScrollingEnabled(true);
        inventoryImage = (ImageView) findViewById(R.id.inventoryImage);
        inventoryText = (TextView) findViewById(R.id.inventoryText);
        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), items);
        simpleList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        simpleList.setAdapter(customAdapter);
        listIdx = 0;

        if (items.size() > 0){
            simpleList.setItemChecked(listIdx, true);
            simpleList.setSelection(listIdx);
            inventoryImage.setImageResource(items.get(listIdx).getImageId());
            inventoryText.setText(items.get(listIdx).getDescription());
        }

        if (items.size() == 0){
            inventoryImage.setImageResource(R.drawable.tumbleweed);
        }
    }

    public void moveBack(View view){
        this.finish();
    }

    public void moveListCursorDown(View cursorDown){
        // nothing happens if list is empty
        if (items.size() == 0){
            return;
        }

        listIdx++;
        listIdx = Math.min(listIdx, items.size()-1);
        simpleList.setItemChecked(listIdx, true);
        simpleList.setSelection(listIdx);
        inventoryImage.setImageResource(items.get(listIdx).getImageId());
        inventoryText.setText(items.get(listIdx).getDescription());
        // scrolling the list down a bit to follow cursor
        if (listIdx != items.size()-1) {
            // simpleList.scrollBy(0, heightToScroll);
        }
    }

    public void moveListCursorUp(View cursorUp){
        // nothing happens if list is empty
        if (items.size() == 0){
            return;
        }

        listIdx--;
        listIdx = Math.max(listIdx, 0);
        simpleList.setItemChecked(listIdx, true);
        simpleList.setSelection(listIdx);
        inventoryImage.setImageResource(items.get(listIdx).getImageId());
        inventoryText.setText(items.get(listIdx).getDescription());
        // scrolling the list up a bit to follow cursor
        if (listIdx != 0) {
            // simpleList.scrollBy(0, -heightToScroll);
        }
    }
}
