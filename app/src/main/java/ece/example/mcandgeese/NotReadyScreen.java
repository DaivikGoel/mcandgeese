package ece.example.mcandgeese;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import ece.example.mcandgeese.R;

import java.io.IOException;

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

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onPause() {
        // Save the user's current game state
        SharedPreferences sharedPreferences = getSharedPreferences(MainActivity.sharedPreferencesKey,MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        GlobalVariables globalVariables = GlobalVariables.getInstance();
        boolean gameStarted = globalVariables.getGameStarted();
        if (gameStarted) {
            try {
                String serializedGame = globalVariables.toString("");
                edit.putString("game", serializedGame);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        edit.commit();
        super.onPause();
    }

    public void goToMapScreen(View view) {
        Intent intent = new Intent(NotReadyScreen.this, MapScreen.class);
        startActivity(intent);
    }
}
