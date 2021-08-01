package com.example.mcandgeese;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class NotReadyScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.not_ready_screen);
        ImageView image = (ImageView) findViewById(R.id.notReady);
        final TypeWriter tw = (TypeWriter) findViewById(R.id.animatedtyping3);
        int imageResource = getResources().getIdentifier("@drawable/not_ready", null, this.getPackageName());
        String notReadyText = "You are not yet ready to face the threat that exists" +
                " beyond this point. Come back when you defeat the enemies that plague our campus.";
        tw.setText("");
        image.setImageResource(imageResource);
        tw.setCharacterDelay(70);
        tw.animateText(notReadyText);
    }

    public void goToMapScreen(View view) {
        Intent intent = new Intent(NotReadyScreen.this, MapScreen.class);
        startActivity(intent);
    }
}
