package com.example.mcandgeese;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class VictoryScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.victory);
        ImageView image = (ImageView) findViewById(R.id.notReady3);
        final TypeWriter tw = (TypeWriter) findViewById(R.id.animatedtyping5);
        int imageResource = getResources().getIdentifier("@drawable/victory", null, this.getPackageName());
        String text = "You have defeated the mighty foe that presided in this area. No " +
                "one will dare disturb this place from now onwards. You are one step closer to" +
                " clearing our beloved campus of our cursed enemies.";
        tw.setText("");
        image.setImageResource(imageResource);
        tw.setCharacterDelay(45);
        tw.animateText(text);
    }

    public void goToBuildingScreen(View view) {
        Intent intent = new Intent(VictoryScreen.this, MapScreen.class);
        startActivity(intent);
    }
}
