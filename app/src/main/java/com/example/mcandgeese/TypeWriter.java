package com.example.mcandgeese;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.widget.TextView;

@SuppressLint("AppCompatCustomView")
public class TypeWriter extends TextView {
    private CharSequence message;
    private int idx;

    // interval between letters in milliseconds
    private long delay = 150;

    public TypeWriter(Context context) {
        super(context);
    }

    private Handler messageHandler = new Handler();
    private Runnable characterStream = new Runnable() {
        @Override
        public void run() {
            setText(message.subSequence(0, idx++));
            if (idx <= message.length()) {
                messageHandler.postDelayed(characterStream, delay);
            }
        }
    };

    public void animateText(CharSequence text){
        message = text;
        idx = 0;
        setText("");
        messageHandler.removeCallbacks(characterStream);
        messageHandler.postDelayed(characterStream, delay);
    }
}
