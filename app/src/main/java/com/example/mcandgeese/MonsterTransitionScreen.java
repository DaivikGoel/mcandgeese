package com.example.mcandgeese;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class MonsterTransitionScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.monster_transition_screen);

        int monsterID = getIntent().getIntExtra("MONSTER_ID", -1);
        String monsterName = getIntent().getStringExtra("MONSTER_NAME");
        String monsterText = "";
        switch(monsterID) {
            case(1):
                monsterText = "Oh my gosh. I've seen these types of animals before. Horrible, vicious, hateful creatures. They've been known to take copious number twos in our cherished park grasses. It's a Goose!";
                break;
            case(2):
                monsterText = "It's Peter Levine! I had him for ECE240. It will be a long, difficult and painful struggle. But we have no choice but to take him on.";
        }


        final TypeWriter tw = (TypeWriter) findViewById(R.id.animatedtyping2);
        tw.setText("");
        tw.setCharacterDelay(70);
        tw.animateText(monsterText);
    }

    public void monsterToBattleScreen(View view) {
        int monsterID = getIntent().getIntExtra("MONSTER_ID", -1);
        Intent intent = new Intent(MonsterTransitionScreen.this, BattleScreen.class);
        intent.putExtra("MONSTER_ID", monsterID);
        startActivity(intent);
    }
}
