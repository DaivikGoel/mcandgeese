package com.example.mcandgeese;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;
import android.content.Intent;
import android.view.View;
import android.net.Uri;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Arrays;

public class transition_screen extends AppCompatActivity {
    VideoView transitionVideo;
    private Uri videPath;
    String pathtovid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition_screen);
        transitionVideo = (VideoView) findViewById(R.id.transitionvideo);

        String videoId = getIntent().getStringExtra("BUILDING_ID");
        pathtovid = "";
        String transitionmessage = "";
        switch (videoId) {
            case ("E5"):
                pathtovid = "android.resource://com.example.mcandgeese/raw/engineering5";
                transitionmessage = "Ah E5, the building of Hack the North and everyone's Linkedin Picture";
                break;
            case ("E7"):
                pathtovid ="android.resource://com.example.mcandgeese/raw/engineering7";
                transitionmessage = " You sure you are in E7? When did E7 begin, E5 Start? We shall never know!";
                break;
            case ("SLC"):
                pathtovid = "android.resource://com.example.mcandgeese/raw/studentlifecentre";
                transitionmessage = "The building of late night Tims, energy drinks and poster sales!";
                break;
            case ("DP"):
                pathtovid ="android.resource://com.example.mcandgeese/raw/danaporter";
                transitionmessage = "Fun fact: They start flashing the lights throughout the building when its closing time. Doesn't stop many from solving that last problem though!";
                break;
            case("Plaza"):
                pathtovid ="android.resource://com.example.mcandgeese/raw/plaza";
                transitionmessage = "The plaza, the perfect combination of cheap food, health infractions and many local staples!";
                break;
            case("QNC"):
                pathtovid = "android.resource://com.example.mcandgeese/raw/nanocentre";
                transitionmessage = "QNC is a super underrated study place. Would highly reccomend. Thank you Mr.Lazardis for another cool building. ";
                break;
            case("begin"):
                pathtovid ="android.resource://com.example.mcandgeese/raw/begin";
                transitionmessage = "The war between man and goose rages on! The geese have taken control of campus and its up to you to free the buildings so students can learn!";
                break;
            case("Food"):
                pathtovid = returnrandomfood();
                transitionmessage = returnfoodmessage(pathtovid);
                break;

        }
        Uri videPath= Uri.parse(pathtovid);


        transitionVideo.setVideoURI(videPath);

        transitionVideo.start();

        final TypeWriter tw = (TypeWriter) findViewById(R.id.animatedtyping);
        tw.setText("");
        tw.setCharacterDelay(70);
        tw.animateText(transitionmessage);
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
        Intent intent = new Intent(transition_screen.this, MapScreen.class);
        startActivity(intent);
    }

    public void navigateToScreen(View view) {
        String imageId = getIntent().getStringExtra("BUILDING_ID");
        Intent intent = new Intent(transition_screen.this, BuildingScreen.class);
        switch (imageId) {
            case("begin"):
                goToMapScreen(view);
                break;
            case ("E5"):
                intent.putExtra("BUILDING_ID", "E5");
                startActivity(intent);
                break;
            case ("E7"):
                intent.putExtra("BUILDING_ID", "E7");
                startActivity(intent);
                break;
            case ("DP"):
                intent.putExtra("BUILDING_ID", "DP");
                startActivity(intent);
                break;
            case ("SLC"):
                intent.putExtra("BUILDING_ID", "SLC");
                startActivity(intent);
                break;
            case ("Food"):
                Intent foodIntent = new Intent(transition_screen.this, FoodScreen.class);
                foodIntent.putExtra("BUILDING_ID", pathtovid);
                startActivity(foodIntent);
                break;
            case ("Plaza"):
                intent.putExtra("BUILDING_ID", "Plaza");
                startActivity(intent);
                break;
            case ("QNC"):
                intent.putExtra("BUILDING_ID", "QNC");
                startActivity(intent);
                break;
        }

    }

    public String returnrandomfood(){
        String path = "android.resource://com.example.mcandgeese/raw/";
        List<String> foodchoices = new ArrayList<>(Arrays.asList("campuspizza", "lazeez", "panino", "sweetdreams"));

        Random rand = new Random();
        path = path + foodchoices.get(rand.nextInt(foodchoices.size()));
        return path;
    }

    public String returnfoodmessage(String foodchoice){
        String message = "";
        switch(foodchoice){
            case("android.resource://com.example.mcandgeese/raw/campuspizza"):
                message = "Campus Pizza, the go to for many uWaterloo students. Either you hate it or you love it, there is no middle ground. My trick is ask for extra sauce!";
                break;
            case("android.resource://com.example.mcandgeese/raw/lazeez"):
                message = "You have chosen Lazeez. Midterms went that badly huh? Might as well drown your sorrows in garlic sauce I guess?";
                break;
            case("android.resource://com.example.mcandgeese/raw/panino"):
                message="Mr.Panino's Beijing House has been a staple for many generation of Waterloo Students. Between the many health inspections and the amount of oil in their food, only the bravest souls go to this place. Chances of survival are 50/50";
                break;
            case("android.resource://com.example.mcandgeese/raw/sweetdreams"):
                message = "Sweet Dreams Bubble Tea Shop is the closest bubble tea shop to campus! If you arent drinking more bubble tea than water, you are clearly doing this Waterloo thing wrong. ";
                break;
            default:
                message = "HOW DID U END UP HERE?? This isn't supposed to be possible. You have found a glitch in the matrix. ";
                break;


        }
        return message;
    }


}