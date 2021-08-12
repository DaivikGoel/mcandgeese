package ece.example.mcandgeese;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import ece.example.mcandgeese.R;

import java.io.IOException;
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
    public String buildingId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GlobalVariables globalVariables = GlobalVariables.getInstance();
        items = globalVariables.getItems() == null ? new ArrayList<>() : globalVariables.getItems();
        buildingId = getIntent().getStringExtra("BUILDING_ID");
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onPause() {
        // Save the user's current game state
        SharedPreferences sharedPreferences = getSharedPreferences(MainActivity.sharedPreferencesKey,MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        GlobalVariables globalVariables = GlobalVariables.getInstance();
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
