package com.example.mcandgeese;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class ScrollableMap extends AppCompatActivity {

    private int userLocX;
    private int userLocY;

//    private int tempX = 1115;
//    private int tempY = 400;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_scrollable_map);
        onWindowFocusChanged(true);

        // set current user location
        this.userLocX = ((GlobalVariables) this.getApplication()).getCurrentLocationX();
        this.userLocY = ((GlobalVariables) this.getApplication()).getCurrentLocationY();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }

        final ImageView StudentV = (ImageView)findViewById(R.id.FreeRoamStudent);
        StudentV.setX(userLocX);
        StudentV.setY(userLocY);
        StudentV.invalidate();
    }

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

    public void goUp(View view) {
        userLocY -= 85;

        drawPlayer();
    }

    public void goLeft(View view) {
        userLocX -= 85;

        drawPlayer();
    }

    public void goRight(View view) {
        userLocX += 85;

        drawPlayer();
    }

    public void goDown(View view) {
        userLocY += 85;

        drawPlayer();
    }
}