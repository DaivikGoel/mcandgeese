package ece.example.mcandgeese;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import ece.example.mcandgeese.R;

import java.io.IOException;
import java.util.HashMap;

public class MapScreen extends AppCompatActivity {
    ImageView firstImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_screen);
        firstImage = (ImageView) findViewById(R.id.firstImage);
        updateMonsterEmoji();
        Button finalEvent = (Button) findViewById(R.id.finalEvent);

        finalEvent.setVisibility(View.GONE);

        int imageResource = getResources().getIdentifier("@drawable/campusmap", null, this.getPackageName());
        firstImage.setImageResource(imageResource);
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

    @Override
    protected void onResume()
    {
        super.onResume();
        updateMonsterEmoji();
        if((GlobalVariables.getInstance()).noMonstersRemain()){
            Button finalEvent = (Button) findViewById(R.id.finalEvent);
            finalEvent.setVisibility(View.VISIBLE);
    }
    }
    public void goToBuildingScreen(View view) {
        HashMap<Integer, Integer> test = (GlobalVariables.getInstance()).getBuildingToMonster();
        System.out.println(test);

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

    public void updateMonsterEmoji(){

        HashMap<Integer, Integer> buildingToMonster = (GlobalVariables.getInstance()).getBuildingToMonster();

        Button E5 = (Button) findViewById(R.id.E5);
        Button E7 = (Button) findViewById(R.id.E7);
        Button DP = (Button) findViewById(R.id.DP);
        Button SLC = (Button) findViewById(R.id.SLC);
        Button QNC = (Button) findViewById(R.id.QNC);
        Button Plaza = (Button) findViewById(R.id.Plaza);
        Button FreeRoam = (Button) findViewById(R.id.FreeRoam);
        Button Mystery = (Button) findViewById(R.id.finalEvent);

        int demon = 0x1F479;
        int bolt = 0x26a1;
        int apple = 0x1F34E;
        int angel = 0x1F607;
        int map = 0x1F5FA;

        String recharge = "/" + getEmojiByUnicode(bolt);
        String clear = "/" + getEmojiByUnicode(angel);

        Mystery.setText("FINAL BOSS" + getEmojiByUnicode(demon));
        FreeRoam.setText("Free Roam" + getEmojiByUnicode(map));

        Plaza.setText("Plaza" + recharge);


        if(buildingToMonster.get(1) != null){

            E5.setText("E5" + getMonsterType(buildingToMonster.get(1)));
        }
        else{
            E5.setText("E5" + clear);

        }
        if(buildingToMonster.get(2) != null){

            E7.setText("E7" + getMonsterType(buildingToMonster.get(2)));
        }
        else{
            E7.setText("E7" + clear);

        }
        if(buildingToMonster.get(3) != null){

            SLC.setText("SLC" + getMonsterType(buildingToMonster.get(3)));
        }
        else{
            SLC.setText("SLC" + clear);

        }

        if(buildingToMonster.get(4) != null){

            DP.setText("DP" + getMonsterType(buildingToMonster.get(4)));
        }
        else{
            DP.setText("DP" + clear);

        }

        if(buildingToMonster.get(5) != null){

            QNC.setText("QNC" + getMonsterType(buildingToMonster.get(5)));
        }
        else{
            QNC.setText("QNC" + clear);

        }
    }

    public String getMonsterType(int monsterid){
        String monster;
        int demon = 0x1F479;
        int bolt = 0x26a1;
        int apple = 0x1F34E;
        int angel = 0x1F607;
        int wood = 0x1FAB5;
        int feather = 0x1FAB6;
        int microbe = 0x1F9A0;
        int crown = 0x1F451;
        int tie = 0x1F454;
        int ghost = 0x1F47B;

        if(monsterid == 1){
            monster = getEmojiByUnicode(feather);
        }
        else if(monsterid == 2){
            monster = getEmojiByUnicode(wood);

        }
        else if(monsterid == 3){
            monster = getEmojiByUnicode(microbe);

        }
        else if(monsterid == 4){
            monster = getEmojiByUnicode(crown);
        }
        else if(monsterid == 5){
            monster = getEmojiByUnicode(tie);
        }
        else if(monsterid == 6){
            monster = getEmojiByUnicode(ghost);
        }
        else{
            monster = getEmojiByUnicode(apple);
        }

        return "/" + monster;
    }


    public void goToFinalEventsScreen(View view) {
        boolean isReady = (GlobalVariables.getInstance()).noMonstersRemain();

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