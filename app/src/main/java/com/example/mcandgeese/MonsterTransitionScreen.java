package com.example.mcandgeese;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class MonsterTransitionScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.monster_transition_screen);
        ImageView image = (ImageView) findViewById(R.id.monsterImage);
        int finalEvent = getIntent().getIntExtra("FINAL_EVENT", 0);
        String finalText = "";
        VideoView video = (VideoView) findViewById(R.id.soundCutscene);
        int imageR = 0;
        if (finalEvent != 0) {
            switch(finalEvent) {
                case(1):
                    finalText = "What is this horrible place? There is an ominous, dreadful sensation that I feel in my bones. " +
                            "It can't be anything good.";
                    imageR = getResources().getIdentifier("@drawable/final1", null, this.getPackageName());
                    break;
                case(2):
                    finalText = "There is something behind that door. My heart is pounding. No matter what it is, we must move forward.";
                    imageR = getResources().getIdentifier("@drawable/final1", null, this.getPackageName());
                    break;
                case(3):
                    finalText = "It looks like we're in the V1 basements. It's in a much better state than when I lived here, just 3 years ago. " +
                            "It's hard to imagine what dangers lurk in these depths.";
                    imageR = getResources().getIdentifier("@drawable/v1_basement", null, this.getPackageName());
                    break;
                case(4):
                    finalText = "Oh no, a resident of this wretched place has fallen victim." +
                            " It looks like midterms did not go easy on him. " +
                            "His spirit lives in us as we tread onwards.";
                    imageR = getResources().getIdentifier("@drawable/tired_student", null, this.getPackageName());
                    break;
                case(5):
                    finalText = "I hear a familiar sound. I think we are approaching the secrets we have been searching for.";
                    imageR = getResources().getIdentifier("@drawable/ear", null, this.getPackageName());
                    String pathtovid ="android.resource://com.example.mcandgeese/raw/laughter";
                    Uri videPath= Uri.parse(pathtovid);
                    video.setVideoURI(videPath);
                    video.start();
                    break;
                case(6):
                    finalText = "Oh no. It can't be. Is that MC? How is that even possible. Aren't we underground? " +
                            "I've always thought something was wrong about that place. Math and Computer? More like misery and calamity." +
                            " Maybe there will be something inside? Let's take a look.";
                    imageR = getResources().getIdentifier("@drawable/mc", null, this.getPackageName());
                    break;
                case(7):
                    finalText = "The ambience here is just as I recall it. The air is moist and humid. I think I smell something? " +
                            "Ah, it's just a Jamaican patty. Let's head towards the fourth floor. Something of immense power must be lurking there.";
                    imageR = getResources().getIdentifier("@drawable/mc_hall", null, this.getPackageName());
                    break;
                case(8):
                    finalText = "What is that? Do you see it? I sense enormous power from it. Is that a human being? But that" +
                            " figure is far too handsome for it to be that simple. Don't tell me. No. Impossible. If that is who I think it is," +
                            " we stand no chance. What am I saying. We have come too far to give up so easily.";
                    imageR = getResources().getIdentifier("@drawable/shadow", null, this.getPackageName());
                    break;
                case(9):
                    finalText = "Is... Is that... The lord of the geese? Ferigoose Of the Goosedullahpurs? This cannot be.";
                    imageR = getResources().getIdentifier("@drawable/goose_god", null, this.getPackageName());
                    String feridunPath ="android.resource://com.example.mcandgeese/raw/ferigoose";
                    Uri videoPath= Uri.parse(feridunPath);
                    video.setVideoURI(videoPath);
                    video.start();
                    break;
            }

            image.setImageResource(imageR);
            final TypeWriter tw = (TypeWriter) findViewById(R.id.animatedtyping2);
            tw.setText("");
            tw.setCharacterDelay(30);
            tw.animateText(finalText);
            return;
        }
        String encounterSound ="android.resource://com.example.mcandgeese/raw/encounter";
        Uri videPath= Uri.parse(encounterSound);
        video.setVideoURI(videPath);
        video.start();
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
                monsterText = "Is that Natural Log? Legends speak of the day this great being was uprooted. It took the combined might" +
                        " of multiple professors. One of who, Peter Levine, was known to many as one of the most powerful individuals to walk this very earth. " +
                        "The fact that the Log returns must mean that the hidden powers ruling this campus are shifting. ";
                imageResource = getResources().getIdentifier("@drawable/natural_log", null, this.getPackageName());
                break;
            case(3):
                monsterText = "Impossible. I believed we defeated Arriba the Amoeba long ago." +
                        " It seems that despite our alumnis' greatest efforts, ancient foes still linger." +
                        " Let us hope that Arriba is but a shadow of her former self. Or I fear " +
                        "our end draws near.";
                imageResource = getResources().getIdentifier("@drawable/arriba_amoeba", null, this.getPackageName());
                break;
            case(4):
                monsterText = "King Warrior. How could this be? The goose droppings must have corrupted him!" +
                        " Many look up to him, and I'm no exception. Yet fate places us here as enemies. " +
                        "In many a day of the past, my comrades and I balled in CIF under his watchful gaze. " +
                        "Yolo swag." +
                        " We must do what we can to take back MC.";
                imageResource = getResources().getIdentifier("@drawable/king_warrior", null, this.getPackageName());
                break;
            case(5):
                monsterText = "Has the math society fallen as well?" +
                        " They were one of the pillars of our school. Without them" +
                        ", the future will be much more difficult. I used to pray that they would shower. Now I just" +
                        " pray for their safety. Amen." ;
                imageResource = getResources().getIdentifier("@drawable/pink", null, this.getPackageName());
                break;
            case(420):
                monsterText = "As the behind the scenes ruler of the coveted University of Waterloo, Ferigoose has gathered enormous power." +
                        " None among goose or man have ever matched up to Him. There is nothing left to say. We must do what" +
                        " we can to stop Him. Before it's too late.";
                imageResource = getResources().getIdentifier("@drawable/final_battle", null, this.getPackageName());
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

    public void monsterToBattleScreen(View view) {
        int finalEvent = getIntent().getIntExtra("FINAL_EVENT", 0);
        Intent intent = new Intent(MonsterTransitionScreen.this, MonsterTransitionScreen.class);
        if (finalEvent != 0) {
            switch(finalEvent) {
                case(1):
                    intent.putExtra("FINAL_EVENT", 2);
                    startActivity(intent);
                    return;
                case(2):
                    intent.putExtra("FINAL_EVENT", 3);
                    startActivity(intent);
                    return;
                case(3):
                    intent.putExtra("FINAL_EVENT", 4);
                    startActivity(intent);
                    return;
                case(4):
                    intent.putExtra("FINAL_EVENT", 5);
                    startActivity(intent);
                    return;
                case(5):
                    intent.putExtra("FINAL_EVENT", 6);
                    startActivity(intent);
                    return;
                case(6):
                    intent.putExtra("FINAL_EVENT", 7);
                    startActivity(intent);
                    return;
                case(7):
                    intent.putExtra("FINAL_EVENT", 8);
                    startActivity(intent);
                    return;
                case(8):
                    intent.putExtra("FINAL_EVENT", 9);
                    startActivity(intent);
                    return;
                default:
                    Monster feridun = Monster.getFeridun();
                    intent.putExtra("MONSTER_ID", feridun.getMonsterID());
                    intent.putExtra("MONSTER_HEALTH", feridun.getMonsterHealth());
                    intent.putExtra("MONSTER_HIT_POINTS", feridun.getMonsterHitPoints());
                    startActivity(intent);
                    return;
            }
        }

        int monsterID = getIntent().getIntExtra("MONSTER_ID", -1);
        int buildingID = getIntent().getIntExtra("BUILDING_ID", -1);
        int monsterHealth = getIntent().getIntExtra("MONSTER_HEALTH", 100);
        int monsterHitPoints = getIntent().getIntExtra("MONSTER_HIT_POINTS", 15);
        intent = new Intent(MonsterTransitionScreen.this, BattleScreen.class);
        intent.putExtra("MONSTER_ID", monsterID);
        intent.putExtra("MONSTER_HEALTH", monsterHealth);
        intent.putExtra("MONSTER_HIT_POINTS", monsterHitPoints);
        intent.putExtra("BUILDING_ID", buildingID);
        startActivity(intent);
    }
}
