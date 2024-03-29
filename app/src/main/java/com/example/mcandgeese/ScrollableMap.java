package com.example.mcandgeese;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.HashMap;

import com.example.mcandgeese.gamePanel.InfoBar;

import org.w3c.dom.Text;

public class ScrollableMap extends AppCompatActivity {

    private int userLocX;
    private int userLocY;

    private int screenHeight;
    private int screenWidth;

    // user info
    private int userHealth;
    private InfoBar userHealthBar;
    private Canvas userHealthCanvas;
    private ImageView userHealthImageView;
    private Bitmap userHealthBitMap;

    // user energy bar
    private int userEnergy;
    private InfoBar userEnergyBar;
    private Canvas userEnergyCanvas;
    private ImageView userEnergyImageView;
    private Bitmap userEnergyBitMap;

    // grid for marking building locations
    private String[][] grid = new String[26][12];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_scrollable_map);
        onWindowFocusChanged(true);

        // initialize grid
        markBuildingLocations();

        // get current user location
        this.userLocX = (GlobalVariables.getInstance()).getCurrentLocationX();
        this.userLocY = (GlobalVariables.getInstance()).getCurrentLocationY();

        // get the user health and energy from state
        this.userHealth = (GlobalVariables.getInstance()).getCurrentHealth();
        this.userEnergy = (GlobalVariables.getInstance()).getCurrentEnergy();

        // get the screen height and width
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.screenHeight = displayMetrics.heightPixels;
        this.screenWidth = displayMetrics.widthPixels;

        setUpUserHealthBar();
        setUpUserEnergyBar();

        updateMonsterEmoji();
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
        if((GlobalVariables.getInstance()).noMonstersRemain()) {
            // MC
            grid[14][3] = "MC";
            grid[15][3] = "MC";

            int demon = 0x1F479;
            TextView finalEvent = (TextView) findViewById(R.id.finalBoss);
            finalEvent.setVisibility(View.VISIBLE);
            finalEvent.setText(getEmojiByUnicode(demon));
            finalEvent.setTextColor(0xff000000);
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }

        // set the user image location
        final ImageView StudentV = (ImageView)findViewById(R.id.FreeRoamStudent);
        StudentV.setX(userLocX);
        StudentV.setY(userLocY);
        StudentV.invalidate();
    }

    private void setUpUserHealthBar() {
        // get the image view reference
        userHealthImageView = (ImageView) findViewById(R.id.userHealthBarFreeRoam);

        userHealthBitMap = Bitmap.createBitmap(
                500, 150, Bitmap.Config.ARGB_8888);

        // Associate the bitmap to the ImageView.
        userHealthImageView.setImageBitmap(userHealthBitMap);

        // Create a Canvas with the bitmap.
        userHealthCanvas = new Canvas(userHealthBitMap);

        // initialize health bar object
        userHealthBar = new InfoBar("#99FF99", 0, 40);

        // draw the health and energy bar
        drawUserHealthBar();
    }
    private void setUpUserEnergyBar() {
        // get the image view reference
        userEnergyImageView = (ImageView) findViewById(R.id.userEnergyBarFreeRoam);

        userEnergyBitMap = Bitmap.createBitmap(
                500, 150, Bitmap.Config.ARGB_8888);

        // Associate the bitmap to the ImageView.
        userEnergyImageView.setImageBitmap(userEnergyBitMap);

        // Create a Canvas with the bitmap.
        userEnergyCanvas = new Canvas(userEnergyBitMap);

        // initialize health bar object
        userEnergyBar = new InfoBar("#3399FF", 0, 40);

        // draw the health and energy bar
        drawUserEnergyBar();
    }

    private void drawUserHealthBar()
    {
        // draw/update health and energy bars
        this.userHealthBar.drawBar(this.userHealth, userHealthCanvas);
    }

    private void drawUserEnergyBar()
    {
        // draw/update health and energy bars
        this.userEnergyBar.drawBar(this.userEnergy, userEnergyCanvas);
    }

    private void markBuildingLocations() {
        // fill empty
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 12; j++) {
                grid[i][j] = "";
            }
        }

        // SLC
        grid[13][3] = "SLC";

        // E7
        grid[20][4] = "E7";

        // E5
        grid[19][4] = "E5";

        // Plaza
        for (int i = 19; i < 23; i++) {
            for (int j = 5; j < 7; j++) {
                grid[i][j] = "Plaza";
            }
        }
        grid[20][7] = "Plaza";

        // QNC
        grid[14][4] = "QNC";

        // DP
        grid[14][7] = "DP";
        grid[15][7] = "DP";

        // Add MC, DC, PAC later
    }

    // function to get rid of the header and footer
    private void hideSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    private void drawPlayer() {
        final ImageView StudentV = (ImageView)findViewById(R.id.FreeRoamStudent);
        StudentV.setX(userLocX);
        StudentV.setY(userLocY);

        // System.out.println(tempX);
        // System.out.println(tempY);
        // System.out.println("\n");

        StudentV.invalidate();
    }

    private void storeUserLocationInState() {
        (GlobalVariables.getInstance()).setCurrentLocation(userLocX, userLocY);
    }

    private void enterBuildingIfPossible() {
        int gridX = userLocX / 85;
        int gridY = userLocY / 85;

        Button enter = (Button) findViewById(R.id.enterButton);
        if (grid[gridX][gridY] != "") {

            if (grid[gridX][gridY] == "MC") {
                // Final Boss
                enter.setText("Final Boss");
            } else {
                // change button text based on building
                enter.setText("Enter " + grid[gridX][gridY]);
            }

            // make button visible if youa are on top of a building
            enter.setVisibility(View.VISIBLE);
        } else {

            // make button invisible
            enter.setVisibility(View.GONE);
        }
    }

    public void goUp(View view) {

        // validate position
        if (userLocY - 85 >= 0) {

            // move the user up
            userLocY -= 85;

            // store updated location in state
            storeUserLocationInState();

            // show enter button if possible to enter building
            enterBuildingIfPossible();

            drawPlayer();
        }
    }

    public void goLeft(View view) {

        // validate position
        if (userLocX - 85 >= 0) {

            // move user to left
            userLocX -= 85;

            // store updated location in state
            storeUserLocationInState();

            // show enter button if possible to enter building
            enterBuildingIfPossible();

            drawPlayer();
        }
    }

    public void goRight(View view) {

        // validate position
        if (userLocX + 85 < screenWidth) {

            // move user right
            userLocX += 85;

            // store updated location in state
            storeUserLocationInState();

            // show enter button if possible to enter building
            enterBuildingIfPossible();

            drawPlayer();
        }
    }

    public void goDown(View view) {

        if (userLocY + 85 < screenHeight) {
            userLocY += 85;

            // store updated location in state
            storeUserLocationInState();

            // show enter button if possible to enter building
            enterBuildingIfPossible();

            drawPlayer();
        }
    }

    public void enterBuilding(View view) {
        int gridX = userLocX / 85;
        int gridY = userLocY / 85;

        if (grid[gridX][gridY] != "") {
            if (grid[gridX][gridY] == "MC") {
                Intent intent = new Intent(ScrollableMap.this, MonsterTransitionScreen.class);
                intent.putExtra("FINAL_EVENT", 1);
                startActivity(intent);
            } else {
                Intent intent = new Intent(ScrollableMap.this, transition_screen.class);
                intent.putExtra("BUILDING_ID", grid[gridX][gridY]);
                startActivity(intent);
            }
        }
    }

    public void goToMapScreen(View view) {
        Intent intent = new Intent(ScrollableMap.this, MapScreen.class);
        startActivity(intent);
    }

    public void updateMonsterEmoji(){

        HashMap<Integer, Integer> buildingToMonster = (GlobalVariables.getInstance()).getBuildingToMonster();

        TextView E5 = (TextView) findViewById(R.id.e5Monster);
        TextView E7 = (TextView) findViewById(R.id.e7Monster);
        TextView DP = (TextView) findViewById(R.id.dpMonster);
        TextView SLC = (TextView) findViewById(R.id.slcMonster);
        TextView QNC = (TextView) findViewById(R.id.qncMonster);
        TextView Plaza = (TextView) findViewById(R.id.plazaCharge);

        int demon = 0x1F479;
        int bolt = 0x26a1;
        int apple = 0x1F34E;
        int angel = 0x1F607;
        int map = 0x1F5FA;

        String recharge = getEmojiByUnicode(bolt);
        String clear = getEmojiByUnicode(angel);

        Plaza.setText(recharge);
        Plaza.setTextColor(0xff000000);

        if(buildingToMonster.get(1) != null){
            // E5.setText("E5" + getMonsterType(buildingToMonster.get(1)));
            // E5.setVisibility(View.VISIBLE);
            E5.setText(getMonsterType(buildingToMonster.get(1)));
        }
        else {
            E5.setText(clear);
        }

        E5.setTextColor(0xff000000);

        if(buildingToMonster.get(2) != null){
            // E7.setText("E7" + getMonsterType(buildingToMonster.get(2)));
            // E7.setVisibility(View.VISIBLE);
            E7.setText(getMonsterType(buildingToMonster.get(2)));
        }
        else {
            E7.setText(clear);
        }

        E7.setTextColor(0xff000000);

        if(buildingToMonster.get(3) != null){
            // SLC.setVisibility(View.VISIBLE);
            SLC.setText(getMonsterType(buildingToMonster.get(3)));
        }
        else {
            SLC.setText(clear);
        }

        SLC.setTextColor(0xff000000);

        if(buildingToMonster.get(4) != null){
            // DP.setText("DP" + getMonsterType(buildingToMonster.get(4)));
            // DP.setVisibility(View.VISIBLE);
            DP.setText(getMonsterType(buildingToMonster.get(4)));
        }
        else{
            DP.setText(clear);

        }

        DP.setTextColor(0xff000000);

        if(buildingToMonster.get(5) != null){
            // QNC.setVisibility(View.VISIBLE);
            QNC.setText(getMonsterType(buildingToMonster.get(5)));
        }
        else{
            QNC.setText(clear);
        }

        QNC.setTextColor(0xff000000);
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

        return monster;
    }

    public String getEmojiByUnicode(int unicode){
        return new String(Character.toChars(unicode));
    }
}