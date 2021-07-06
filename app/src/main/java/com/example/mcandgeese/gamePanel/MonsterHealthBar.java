package com.example.mcandgeese.gamePanel;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;

public class MonsterHealthBar {

    private int MAX_MONSTER_HEALTH = 100;
    private float curHealthPercentage = 100;
    private Paint healthPaint;
    private Paint textPaint;

    // constructor
    public MonsterHealthBar() {

        // color for health bar
        this.healthPaint = new Paint();
        healthPaint.setColor(Color.parseColor("#FF0000"));

        // color and text size for health/energy texts
        this.textPaint = new Paint();
        textPaint.setColor(Color.parseColor("#000000"));
        textPaint.setTextSize(25);
    }

    // draw the healthBar
    public void drawBar(int curHealth, Canvas canvas) {
        curHealthPercentage = (float) curHealth / MAX_MONSTER_HEALTH;

        float healthLeft = 100;
        float healthWidth = healthLeft + (canvas.getWidth() - healthLeft) * curHealthPercentage;

        // clear canvas so that rectangle can be redrawn
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);

        // draw health bar
        canvas.drawText("Health", 0,25, this.textPaint);
        canvas.drawRect(healthLeft, 0, healthWidth, 40, this.healthPaint);
    }
}

