package com.example.mcandgeese;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mcandgeese.gamePanel.MonsterHealthBar;
import com.example.mcandgeese.gamePanel.UserHealthBar;

public class BattleScreen extends AppCompatActivity {

    int userhealth; //Remaining User Health (need to be set from previous state/battle)
    int energy; //Remaining User Energy (need to be set from previous state/battle)
    int monsterhealth = 100; // Starting Monster Health
    int monsterhitpoint = 10; // Amount of Damage to user (per turn)
    int attackenergycost = 25; // Cost of energy per attack to monster
    int healenergycost = 5; //Cost of energy per heal to user
    int hitpoint = 20; // Amount of damage per attack to monster
    int healpoint = 15; // Amount user heals per heal
    int rechargeamount = 10; // Amount user energy increases per recharge
    //need to load in counter that is globally held


    int x_cord = 606; //606
    int y_cord = 65;//65

    // user info
    private UserHealthBar userHealthBar;
    private Canvas userCanvas;
    private ImageView userImageView;
    private Bitmap userBitMap;

    // monster info
    private MonsterHealthBar monsterHealthBar;
    private Canvas monsterCanvas;
    private ImageView monsterImageView;
    private Bitmap monsterBitMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.userhealth = ((GlobalVariables) this.getApplication()).getCurrentHealth();
        this.energy = ((GlobalVariables) this.getApplication()).getCurrentEnergy();
        setContentView(R.layout.battle_setup);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }

        final ImageView imageView = (ImageView)findViewById(R.id.Student);
        imageView.setX(x_cord);
        imageView.setY(y_cord);
        imageView.invalidate();
    }

    private void hideSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    public void ArrowUp(View view) {
        y_cord = y_cord -85;

        final ImageView imageView = (ImageView)findViewById(R.id.Student);
        imageView.setY(y_cord);
        imageView.invalidate();

    }

    public void ArrowDown(View view) {
        y_cord = y_cord +85;

        final ImageView imageView = (ImageView)findViewById(R.id.Student);
        imageView.setY(y_cord);
        imageView.invalidate();
    }

    public void ArrowLeft(View view) {
        x_cord = x_cord -85;

        final ImageView imageView = (ImageView)findViewById(R.id.Student);
        imageView.setX(x_cord);
        imageView.invalidate();
    }

    public void ArrowRight(View view) {
        x_cord = x_cord +85;

        final ImageView imageView = (ImageView)findViewById(R.id.Student);
        imageView.setX(x_cord);
        imageView.invalidate();

    }


    public void AttackMonster(View view) { // Runs when user presses 'ATTACK' Button
        if (energy >= attackenergycost)
        {
            monsterhealth = monsterhealth - hitpoint;
            energy = energy - attackenergycost;
            ((GlobalVariables) this.getApplication()).setCurrentEnergy(energy);

            drawUserBars();
            drawMonsterBar();

            if (monsterhealth <= 0) { EndGameWin(); }
            else { MonsterTurn();}
        }
        else
        {
            TextView textView = findViewById(R.id.textView);
            textView.setText("Not Enough Energy!");
        }

    }

    public void HealUser(View view) { // Runs when user presses 'HEAL'
        if (energy >= healenergycost)
        {
            userhealth = userhealth + healpoint;
            ((GlobalVariables) this.getApplication()).setCurrentEnergy(userhealth);
            energy = energy - healenergycost;
            ((GlobalVariables) this.getApplication()).setCurrentEnergy(energy);



            // update the health and energy bars
            drawUserBars();
            drawMonsterBar();

            MonsterTurn();
        }
        else
        {
            TextView textView = findViewById(R.id.textView);
            textView.setText("Not Enough Energy!");
        }
    }

    public void RechargeUser(View view) // Runs when user presses 'RECHARGE'
    {
        energy = energy + rechargeamount;
        ((GlobalVariables) this.getApplication()).setCurrentEnergy(energy);


        drawUserBars();
        drawMonsterBar();

        MonsterTurn();

    }

    public void MonsterTurn() // Runs after any button is pressed, This is the monsters attack
    {
        TextView textView = findViewById(R.id.textView);
        textView.setText("Monster Turn!");

        userhealth = userhealth - monsterhitpoint ;
        ((GlobalVariables) this.getApplication()).setCurrentHealth(userhealth);

        // update health and energy bars
        drawUserBars();
        drawMonsterBar();

        if (userhealth <= 0) { EndGameLoose(); }

        textView.setText("User Turn!");
    }


    /*

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.userhealth = ((GlobalVariables) this.getApplication()).getCurrentHealth();
        this.energy = ((GlobalVariables) this.getApplication()).getCurrentEnergy();
        setContentView(R.layout.activity_battle_screen);


        TextView BC = findViewById(R.id.textView12);
        BC.setText(String.valueOf(hitpoint));

        TextView CD = findViewById(R.id.textView13);
        CD.setText(String.valueOf(attackenergycost));

        TextView EF = findViewById(R.id.textView17);
        EF.setText(String.valueOf(healpoint));

        TextView FG = findViewById(R.id.textView18);
        FG.setText(String.valueOf(healenergycost));

        TextView GH = findViewById(R.id.textView19);
        GH.setText(String.valueOf(rechargeamount));

        TextView textView = findViewById(R.id.textView);
        textView.setText("User Turn!");

        // setup the user health and energy bars
        setUpUserHealthAndEnergyBars();

        // setup the monster health bar
        setUpMonsterHealthBar();
    }

    private void setUpUserHealthAndEnergyBars() {
        // get the image view reference
        userImageView = (ImageView) findViewById(R.id.userHealthBar);

        userBitMap = Bitmap.createBitmap(
                700, 150, Bitmap.Config.ARGB_8888);

        // Associate the bitmap to the ImageView.
        userImageView.setImageBitmap(userBitMap);

        // Create a Canvas with the bitmap.
        userCanvas = new Canvas(userBitMap);

        // initialize health bar object
        userHealthBar = new UserHealthBar();

        // draw the health and energy bar
        drawUserBars();
    }

    private void setUpMonsterHealthBar() {
        // get the image view reference
        monsterImageView = (ImageView) findViewById(R.id.monsterHealthBar);

        monsterBitMap = Bitmap.createBitmap(
                700, 150, Bitmap.Config.ARGB_8888);

        // Associate the bitmap to the ImageView.
        monsterImageView.setImageBitmap(monsterBitMap);

        // Create a Canvas with the bitmap.
        monsterCanvas = new Canvas(monsterBitMap);

        // initialize health bar object
        monsterHealthBar = new MonsterHealthBar();

        // draw the health and energy bar
        drawMonsterBar();
    }



    */

    public void EndGameWin() // if user wins
    {
        //Need to import counter for number of remaining monsters and if counter ==0 then user has completed game
        //setContentView(R.layout.user_win_final

        setContentView(R.layout.user_win_return);
    }

    public void EndGameLoose() // if user looses
    {
        // On loss, reset the user's health and energy
        resetStats();
        setContentView(R.layout.user_loose);
    }

    public void drawUserBars() {

        // draw/update health and energy bars
        this.userHealthBar.drawBar(userhealth, energy, userCanvas);
    }

    public void drawMonsterBar() {
        // draw/update health and energy bars
        this.monsterHealthBar.drawBar(monsterhealth, monsterCanvas);
    }

    public void resetStats() {
        ((GlobalVariables) this.getApplication()).setCurrentHealth(100);
        ((GlobalVariables) this.getApplication()).setCurrentEnergy(100);
    }

    public void goToMapScreen(View view) {
        Intent intent = new Intent(BattleScreen.this, MapScreen.class);
        startActivity(intent);
    }

    public void goToBuildingScreen(View view) {
        Intent intent = new Intent(BattleScreen.this, BuildingScreen.class);
        startActivity(intent);
    }

    public void goToMainScreen(View view) {
        Intent intent = new Intent(BattleScreen.this, MainActivity.class);
        startActivity(intent);
    }
}