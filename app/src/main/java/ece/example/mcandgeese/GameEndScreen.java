package ece.example.mcandgeese;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import ece.example.mcandgeese.R;

public class GameEndScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.final_victory);
        ImageView image = (ImageView) findViewById(R.id.finalVictory);
        final TypeWriter tw = (TypeWriter) findViewById(R.id.animatedtyping4);
        int imageResource = getResources().getIdentifier("@drawable/anime_girl", null, this.getPackageName());
        String notReadyText = "You are the savior of our beloved University. " +
                "May your interviews and rankings go well. " +
                "May your Lazeez have many lines of sauce. " +
                "May your shoes avoid the droppings of geese wherever you may go. " +
                "Amen";
        tw.setText("");
        image.setImageResource(imageResource);
        tw.setCharacterDelay(40);
        tw.animateText(notReadyText);
    }

    public void goToBeginning(View view) {
        Intent intent = new Intent(GameEndScreen.this, MainActivity.class);
        startActivity(intent);
    }
}
