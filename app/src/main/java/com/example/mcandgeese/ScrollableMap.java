package com.example.mcandgeese;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class ScrollableMap extends AppCompatActivity {

    private int userLocX;
    private int userLocY;

//    private int tempX = 1115;
//    private int tempY = 400;

    private int screenHeight;
    private int screenWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_scrollable_map);
        onWindowFocusChanged(true);

        // set current user location
        this.userLocX = ((GlobalVariables) this.getApplication()).getCurrentLocationX();
        this.userLocY = ((GlobalVariables) this.getApplication()).getCurrentLocationY();

        // get the screen height and width
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.screenHeight = displayMetrics.heightPixels;
        this.screenWidth = displayMetrics.widthPixels;
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

    public void goUp(View view) {

        // validate position
        if (userLocY - 85 >= 0) {

            // move the user up
            userLocY -= 85;

            storeUserLocationInState();

            drawPlayer();
        }
    }

    public void goLeft(View view) {

        // validate position
        if (userLocX - 85 >= 0) {

            // move user to left
            userLocX -= 85;

            storeUserLocationInState();

            drawPlayer();
        }
    }

    public void goRight(View view) {

        // validate position
        if (userLocX + 85 < screenWidth) {

            // move user right
            userLocX += 85;

            storeUserLocationInState();

            drawPlayer();
        }
    }

    public void goDown(View view) {

        if (userLocY + 85 < screenHeight) {
            userLocY += 85;

            storeUserLocationInState();

            drawPlayer();
        }
    }
}