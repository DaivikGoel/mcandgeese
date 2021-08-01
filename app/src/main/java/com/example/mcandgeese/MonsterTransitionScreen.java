package com.example.mcandgeese;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class MonsterTransitionScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.monster_transition_screen);
        ImageView image = (ImageView) findViewById(R.id.monsterImage);

        int monsterID = getIntent().getIntExtra("MONSTER_ID", -1);
        String monsterName = getIntent().getStringExtra("MONSTER_NAME");
        String monsterText = "";
        int imageResource = getResources().getIdentifier("@drawable/campusmap", null, this.getPackageName());
        switch(monsterID) {
            case(1):
                monsterText = "Oh my gosh. I've seen these types of animals before. Horrible, vicious, hateful creatures. They've been known to take copious number twos in our cherished park grasses. It's a Goose!";
                imageResource = getResources().getIdentifier("@drawable/goosemad", null, this.getPackageName());
                break;
            case(2):
                monsterText = "It's Peter Levine! I had him for ECE240. It will be a long, difficult and painful struggle. But we have no choice but to take him on.";
                imageResource = getResources().getIdentifier("@drawable/pmlevine", null, this.getPackageName());
                break;
            case(3):
                monsterText = "Impossible. I believed we defeated Arriba the Amoeba long ago." +
                        " It seems that despite our alumnis' greatest efforts, ancient foes still linger." +
                        " Let us hope that Arriba is but a shadow of her former self. Or I fear " +
                        "our end draws near.";
                imageResource = getResources().getIdentifier("@drawable/arriba_amoeba", null, this.getPackageName());
                break;
            default:
                monsterText = "We have already defeated an enemy here" +
                        " The opponent was a powerful one, but it looks like we have " +
                        "cleared this area of the plague that is geese.";
                imageResource = getResources().getIdentifier("@drawable/empty_battlefield", null, this.getPackageName());
        }
        image.setImageResource(imageResource);

        final TypeWriter tw = (TypeWriter) findViewById(R.id.animatedtyping2);
        tw.setText("");
        tw.setCharacterDelay(35);
        tw.animateText(monsterText);
    }

    public void monsterToBattleScreen(View view) {
        int monsterID = getIntent().getIntExtra("MONSTER_ID", -1);
        int monsterHealth = getIntent().getIntExtra("MONSTER_HEALTH", 100);
        int monsterHitPoints = getIntent().getIntExtra("MONSTER_HIT_POINTS", 15);
        Intent intent = new Intent(MonsterTransitionScreen.this, BattleScreen.class);
        intent.putExtra("MONSTER_ID", monsterID);
        intent.putExtra("MONSTER_HEALTH", monsterHealth);
        intent.putExtra("MONSTER_HIT_POINTS", monsterHitPoints);
        startActivity(intent);
    }
}
