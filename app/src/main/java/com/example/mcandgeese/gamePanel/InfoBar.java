package com.example.mcandgeese.gamePanel;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;

public class InfoBar {
    private int MAX_INFO = 100;
    private float curPercentage = 100;
    private Paint paint;
    private int infoTop;
    private int infoBottom;

    // constructor
    public InfoBar(String color, int top, int bottom) {

        // color for info bar
        this.paint = new Paint();
        paint.setColor(Color.parseColor(color));

        // set the location
        this.infoTop = top;
        this.infoBottom = bottom;
    }

    // draw the infoBar
    public void drawBar(int curVal, Canvas canvas) {
        curPercentage = (float) curVal / MAX_INFO;

        float infoLeft = 0;
        float infoWidth = infoLeft + (canvas.getWidth() - infoLeft) * curPercentage;

        // clear canvas so that rectangle can be redrawn
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);

        // draw health bar
        canvas.drawRect(infoLeft, this.infoTop, infoWidth, this.infoBottom, this.paint);
    }
}

