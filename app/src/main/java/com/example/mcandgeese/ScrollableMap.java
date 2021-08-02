package com.example.mcandgeese;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.mcandgeese.gamePanel.InfoBar;

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
        this.userLocX = ((GlobalVariables) this.getApplication()).getCurrentLocationX();
        this.userLocY = ((GlobalVariables) this.getApplication()).getCurrentLocationY();

        // get the user health and energy from state
        this.userHealth = ((GlobalVariables) this.getApplication()).getCurrentHealth();
        this.userEnergy = ((GlobalVariables) this.getApplication()).getCurrentEnergy();

        // get the screen height and width
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.screenHeight = displayMetrics.heightPixels;
        this.screenWidth = displayMetrics.widthPixels;

        setUpUserHealthBar();
        setUpUserEnergyBar();
        
        System.out.println(this.screenWidth);
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
        ((GlobalVariables) this.getApplication()).setCurrentLocation(userLocX, userLocY);
    }

    private void enterBuildingIfPossible() {
        int gridX = userLocX / 85;
        int gridY = userLocY / 85;

        Button enter = (Button) findViewById(R.id.enterButton);
        if (grid[gridX][gridY] != "") {

            // change button text based on building
            enter.setText("Enter " + grid[gridX][gridY]);

            // make button visible if youa are on top of a building
            enter.setVisibility(View.VISIBLE);
        } else {

            // make button invisible
            enter.setVisibility(View.GONE);
        }

        System.out.println("\n");
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
            Intent intent = new Intent(ScrollableMap.this, transition_screen.class);
            intent.putExtra("BUILDING_ID", grid[gridX][gridY]);
            startActivity(intent);
        }
    }
}