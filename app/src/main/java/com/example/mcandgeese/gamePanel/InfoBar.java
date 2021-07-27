package com.example.mcandgeese.gamePanel;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;

public class InfoBar {
    private int MAX_INFO = 100;
    private float curPercentage = 100;
    private Paint paint;
    private Paint textPaint;
    private int infoTop;
    private int infoBottom;

    // constructor
    public InfoBar(int color, int top, int bottom) {

        // color for info bar
        this.paint = new Paint();
        paint.setColor(color);

        // set the location
        this.infoTop = top;
        this.infoBottom = bottom;
    }

    // draw the infoBar
    public void drawBar(int curVal, Canvas canvas) {
        curPercentage = (float) curVal / MAX_INFO;
        // curEnergyPercentage = (float) curEnergy / MAX_USER_ENERGY;

        float infoLeft = 100;
        float infoWidth = infoLeft + (canvas.getWidth() - infoLeft) * curPercentage;
        // float energyWidth = healthLeft + (canvas.getWidth() - healthLeft) * curEnergyPercentage;

        // clear canvas so that rectangle can be redrawn
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);

        // draw health bar
        // canvas.drawText("Health", 0,25, this.textPaint);
        canvas.drawRect(infoLeft, this.infoTop, infoWidth, this.infoBottom, this.paint);
    }
}

