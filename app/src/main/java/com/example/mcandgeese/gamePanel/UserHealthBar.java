package com.example.mcandgeese.gamePanel;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;

public class UserHealthBar {

    private int MAX_USER_HEALTH = 100;
    private int MAX_USER_ENERGY = 100;
    private float curHealthPercentage = 100;
    private float curEnergyPercentage = 100;
    private Paint healthPaint;
    private Paint energyPaint;
    private Paint textPaint;

    // constructor
    public UserHealthBar() {

        // color for health bar
        this.healthPaint = new Paint();
        healthPaint.setColor(Color.parseColor("#99FF99"));

        // color for energy bar
        this.energyPaint = new Paint();
        energyPaint.setColor(Color.parseColor("#3399FF"));

        // color and text size for health/energy texts
        this.textPaint = new Paint();
        textPaint.setColor(Color.parseColor("#000000"));
        textPaint.setTextSize(25);
    }

    // draw the healthBar
    public void drawBar(int curHealth, int curEnergy, Canvas canvas) {
        curHealthPercentage = (float) curHealth / MAX_USER_HEALTH;
        curEnergyPercentage = (float) curEnergy / MAX_USER_ENERGY;

        float healthLeft = 100;
        float healthWidth = healthLeft + (canvas.getWidth() - healthLeft) * curHealthPercentage;
        float energyWidth = healthLeft + (canvas.getWidth() - healthLeft) * curEnergyPercentage;

        // clear canvas so that rectangle can be redrawn
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);

        // draw health bar
        canvas.drawText("Health", 0,25, this.textPaint);
        canvas.drawRect(healthLeft, 0, healthWidth, 40, this.healthPaint);

        // draw energy bar
        canvas.drawText("Energy", 0,75, this.textPaint);
        canvas.drawRect(healthLeft, 50, energyWidth, 90, this.energyPaint);
    }
}
