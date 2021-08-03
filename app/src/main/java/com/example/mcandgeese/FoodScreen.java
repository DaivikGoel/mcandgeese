package com.example.mcandgeese;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FoodScreen extends AppCompatActivity {
    ListView simpleList;
    ImageView foodImage;
    TextView foodText;
    TextView currentEnergy;
    TextView currentWatcard;
    int listIdx;
    int balance;
    int energy;

    // height in px to be scrolled each time
    final int heightToScroll = 50;
    List<Item> foodItems;
    List<Item> items;
    public String buildingId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_screen);

        simpleList = (ListView) findViewById(R.id.foodListView);
        simpleList.setNestedScrollingEnabled(true);
        foodImage = (ImageView) findViewById(R.id.foodImage);
        foodText = (TextView) findViewById(R.id.foodText);
        currentEnergy = (TextView) findViewById(R.id.energyFood);
        currentWatcard = (TextView) findViewById(R.id.watcardFood);
        GlobalVariables globalVariables = (GlobalVariables) this.getApplication();
        items = globalVariables.getItems() == null ? new ArrayList<>() : globalVariables.getItems();
        balance = 0;
        for (Item item : items){
            if (item.getName() == "WatCard Balance"){
                balance = item.getQuantity();
            }
        }
        energy = globalVariables.currentEnergy;
        currentWatcard.setText("Current Balance: " + balance);
        currentEnergy.setText("Current Energy: " + energy);

        buildingId = getIntent().getStringExtra("BUILDING_ID");
        String message;
        switch(buildingId){
            case("android.resource://com.example.mcandgeese/raw/campuspizza"):
                message = "\n \n \n \n Small Items ($5) " +
                        "replenish 5 energy \n Medium ($10) replenishes 10 \n Large ($25) replenishes 25";
                foodItems = new ArrayList<>(Arrays.asList(Item.getCampusSingle(), Item.getCampusBigKahuna(), Item.getCampusBigKahunaSauce()));
                break;
            case("android.resource://com.example.mcandgeese/raw/lazeez"):
                message = "\n \n \n \n Small Items ($5) " +
                        "replenish 5 energy \n Medium ($10) replenishes 10 \n Large ($25) replenishes 25";
                foodItems = new ArrayList<>(Arrays.asList(Item.getLazeezSingle(), Item.getLazeezTriple(), Item.getLazeezExtra()));
                break;
            case("android.resource://com.example.mcandgeese/raw/panino"):
                message="\n \n \n \n Small Items ($5) " +
                        "replenish 5 energy \n Medium ($10) replenishes 10 \n Large ($25) replenishes 25";
                foodItems = new ArrayList<>(Arrays.asList(Item.getPaninoMapo(), Item.getPaninoDumpling(), Item.getPaninoChicken()));
                break;
            case("android.resource://com.example.mcandgeese/raw/sweetdreams"):
                message = "\n \n \n \n Small Items ($5) " +
                        "replenish 5 energy \n Medium ($10) replenishes 10 \n Large ($25) replenishes 25";
                foodItems = new ArrayList<>(Arrays.asList(Item.getSweetMango(), Item.getSweetCaramelt(), Item.getSweetGrilled()));
                break;
            default:
                message = "LOL THIS IS AN ERROR IN THE MATRIX";
                foodItems = new ArrayList<>();
                break;
        }

        foodText.setText(message);
        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), foodItems);
        simpleList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        simpleList.setAdapter(customAdapter);
        listIdx = 0;

        if (foodItems.size() > 0){
            simpleList.setItemChecked(listIdx, true);
            simpleList.setSelection(listIdx);
            foodImage.setImageResource(foodItems.get(listIdx).getImageId());
        }

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

    public void moveBack(View view){
        Intent intent = new Intent(FoodScreen.this, transition_screen.class);
        intent.putExtra("BUILDING_ID", "Plaza");
        startActivity(intent);
    }

    public void buy(View view){
        int price = foodItems.get(listIdx).getQuantity();
        if (balance >= price && energy != 100){
            balance -= price;
            for (Item item : items){
                if (item.getName() == "WatCard Balance"){
                    item.setQuantity(balance);
                }
            }
            currentWatcard.setText("Current Balance: " + balance);

            energy = Math.min(energy+balance, 100);
            currentEnergy.setText("Current Energy: " + energy);
            GlobalVariables globalVariables = (GlobalVariables) this.getApplication();
            globalVariables.setItems(items);
            globalVariables.setCurrentEnergy(energy);
        } else {
            if (energy == 100){
                Snackbar snackbar = Snackbar.make(view, "Energy is Already Full", Snackbar.LENGTH_LONG);
                snackbar.show();
            } else {
                Snackbar snackbar = Snackbar.make(view, "Not enough funds", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        }
    }

    public void moveListCursorDown(View cursorDown){
        // nothing happens if list is empty
        if (foodItems.size() == 0){
            return;
        }

        listIdx++;
        listIdx = Math.min(listIdx, foodItems.size()-1);
        simpleList.setItemChecked(listIdx, true);
        simpleList.setSelection(listIdx);
        foodImage.setImageResource(foodItems.get(listIdx).getImageId());
        // scrolling the list down a bit to follow cursor
        if (listIdx != foodItems.size()-1) {
            // simpleList.scrollBy(0, heightToScroll);
        }
    }

    public void moveListCursorUp(View cursorUp){
        // nothing happens if list is empty
        if (foodItems.size() == 0){
            return;
        }

        listIdx--;
        listIdx = Math.max(listIdx, 0);
        simpleList.setItemChecked(listIdx, true);
        simpleList.setSelection(listIdx);
        foodImage.setImageResource(foodItems.get(listIdx).getImageId());
        // scrolling the list up a bit to follow cursor
        if (listIdx != 0) {
            // simpleList.scrollBy(0, -heightToScroll);
        }
    }
}
