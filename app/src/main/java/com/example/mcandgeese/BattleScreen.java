package com.example.mcandgeese;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mcandgeese.gamePanel.InfoBar;

import java.io.IOException;
import java.util.Random;

import static java.lang.Math.abs;

public class BattleScreen extends AppCompatActivity {

    int userhealth; //Remaining User Health (need to be set from previous state/battle)
    int energy; //Remaining User Energy (need to be set from previous state/battle)
    int monsterhealth; // Starting Monster Health
    int monsterhitpoint; // Amount of Damage to user (per turn)
 //   int attackenergycost = 25; // Cost of energy per attack to monster
    int healenergycost = 5; //Cost of energy per heal to user
    int hitpoint = 20; // Amount of damage per attack to monster
    int healpoint = 15; // Amount user heals per heal
    int rechargeamount = 10; // Amount user energy increases per recharge
    int movementcost = 3;
    int buildingID;
    int monsterID;
    //need to load in counter that is globally held


    int x_s = 605; //graphical value
    int y_s = 400;

    int x_g = 1710;
    int y_g = 400;

    int[][] map = new int[10][28];
    String forprint1,forprint2,forprint3,forprint4,forprint5,forprint6,forprint7,forprint8,forprint9,forprint10;
    int mx_s = 8; //coordinate value
    int my_s = 4;

    int mx_g = 21;
    int my_g = 4;

    int value =0;

    int[][] boulderlocation = new int[5][2];
    int randomx;
    int randomy;

    // user info
    private InfoBar userHealthBar;
    private Canvas userHealthCanvas;
    private ImageView userHealthImageView;
    private Bitmap userHealthBitMap;

    // user energy bar
    private InfoBar userEnergyBar;
    private Canvas userEnergyCanvas;
    private ImageView userEnergyImageView;
    private Bitmap userEnergyBitMap;

    // monster info
    private InfoBar monsterHealthBar;
    private Canvas monsterCanvas;
    private ImageView monsterImageView;
    private Bitmap monsterBitMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.userhealth = ((GlobalVariables) this.getApplication()).getCurrentHealth();
        this.energy = ((GlobalVariables) this.getApplication()).getCurrentEnergy();
        int monsterHealth = getIntent().getIntExtra("MONSTER_HEALTH", 100);
        int monsterHitPoints = getIntent().getIntExtra("MONSTER_HIT_POINTS", 15);
        this.monsterID = getIntent().getIntExtra("MONSTER_ID", -1);
        this.buildingID = getIntent().getIntExtra("BUILDING_ID", -1);
        this.monsterhealth = monsterHealth;
        this.monsterhitpoint = monsterHitPoints;
        this.
        setContentView(R.layout.battle_setup);
        onWindowFocusChanged(true);
        setupmap();
        startlocations();
        BoulderSetUp();
        UpdateGrid();


        // setup the user health/energy and monster health bars
        setUpUserHealthBar();
        setUpUserEnergyBar();
        setUpMonsterHealthBar();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onPause() {
        // Save the user's current game state
        SharedPreferences sharedPreferences = getSharedPreferences(MainActivity.sharedPreferencesKey,MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        GlobalVariables globalVariables = (GlobalVariables) this.getApplication();
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

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }

        final ImageView StudentV = (ImageView)findViewById(R.id.Student);
        StudentV.setX(x_s);
        StudentV.setY(y_s);
        StudentV.invalidate();
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

    private void setUpUserHealthBar() {
        // get the image view reference
        userHealthImageView = (ImageView) findViewById(R.id.userhealthbar);

        userHealthBitMap = Bitmap.createBitmap(
                500, 150, Bitmap.Config.ARGB_8888);

        // Associate the bitmap to the ImageView.
        userHealthImageView.setImageBitmap(userHealthBitMap);

        // Create a Canvas with the bitmap.
        userHealthCanvas = new Canvas(userHealthBitMap);

        // initialize health bar object
        userHealthBar = new InfoBar("#99FF99", 0, 40);

        // draw the health and energy bar
        drawUserHealthBar();
    }
    private void setUpUserEnergyBar() {
        // get the image view reference
        userEnergyImageView = (ImageView) findViewById(R.id.userenergybar);

        userEnergyBitMap = Bitmap.createBitmap(
                500, 150, Bitmap.Config.ARGB_8888);

        // Associate the bitmap to the ImageView.
        userEnergyImageView.setImageBitmap(userEnergyBitMap);

        // Create a Canvas with the bitmap.
        userEnergyCanvas = new Canvas(userEnergyBitMap);

        // initialize health bar object
        userEnergyBar = new InfoBar("#3399FF", 0, 40);

        // draw the health and energy bar
        drawUserEnergyBar();
    }
    private void setUpMonsterHealthBar() {
        // get the image view reference
        monsterImageView = (ImageView) findViewById(R.id.monsterhealthbar);

        monsterBitMap = Bitmap.createBitmap(
                700, 150, Bitmap.Config.ARGB_8888);

        // Associate the bitmap to the ImageView.
        monsterImageView.setImageBitmap(monsterBitMap);

        // Create a Canvas with the bitmap.
        monsterCanvas = new Canvas(monsterBitMap);

        // initialize health bar object
        monsterHealthBar = new InfoBar("#FF0000", 0, 40);

        // draw the health and energy bar
        drawMonsterBar();
    }




    public void setupmap()
    {
        for(int i = 1; i <3; i++)//set out of bounds
        {
            for(int j =1; j<7; j++)
            {
                map[i][j] = 5;
            }
        }

        for(int l = 0; l <28; l++)//set out of bounds
        {
            map[0][l] = 5;
            map[9][l] = 5;
        }

        for(int k =0; k<10; k++)
        {
            map[k][0] = 5;
            map[k][27] = 5;
        }

        for(int j=0;j<5;j++){
            map[boulderlocation[j][1]][boulderlocation[j][0]] = 5;
        }
    }

    public void startlocations()
    {
        map[my_s][mx_s] = 1; //student location

        map[my_s][mx_s+1] = 2; //student range
        map[my_s][mx_s-1] = 2;
        map[my_s+1][mx_s] = 2;
        map[my_s-1][mx_s] = 2;


        map[my_g][mx_g] =3; //goose location

        map[my_g+1][mx_g+1] =4; //goose range
        map[my_g-1][mx_g+1] =4;
        map[my_g+1][mx_g-1] =4;
        map[my_g-1][mx_g-1] =4;
    }

    public void BoulderSetUp()
    {
      //  int blx;
       // int bly;

        for (int i=0;i<5;i++)
        {
        randomx = new Random().nextInt(26) + 1;
        randomy = new Random().nextInt(7) + 1;

        if (map[randomy][randomx]==0) {
     //       blx = ((randomx - 1) * 85);// + 10;
      //      bly = ((randomy - 1) * 85);// - 60;

            boulderlocation[i][0]= randomx;
            boulderlocation[i][1]= randomy;
        }
            else
            {
                i=i-1;
            }
        }
        DisplayBoulder();

    }

    public void UpdateGrid(){
        for(int i = 0; i <10; i++)//clear grid
        {
            for(int j =0; j<28; j++)
            {
                map[i][j] = 0;
            }
        }


        map[my_s][mx_s+1] = 2; //student range
        map[my_s][mx_s-1] = 2;
        map[my_s+1][mx_s] = 2;
        map[my_s-1][mx_s] = 2;


        map[my_g+1][mx_g+1] = 4; //goose range
        map[my_g-1][mx_g+1] = 4;
        map[my_g+1][mx_g-1] = 4;
        map[my_g-1][mx_g-1] = 4;

        map[my_s][mx_s] = 1; //student location
        map[my_g][mx_g] = 3; //goose location
        setupmap();

    }

    public void GooseTurn(){
        int direction = 0;

        //if student within range -student_health
        if (map[my_g+1][mx_g+1] == 1){
            userhealth = userhealth - monsterhitpoint;
            ((GlobalVariables) this.getApplication()).setCurrentHealth(userhealth);
            CheckUserHealth();
            drawBars();
        }
        else if (map[my_g-1][mx_g+1] == 1){
            userhealth = userhealth - monsterhitpoint;
            ((GlobalVariables) this.getApplication()).setCurrentHealth(userhealth);
            CheckUserHealth();
            drawBars();
        }
        else if (map[my_g+1][mx_g-1] == 1){
            userhealth = userhealth - monsterhitpoint;
            ((GlobalVariables) this.getApplication()).setCurrentHealth(userhealth);
            CheckUserHealth();
            drawBars();
        }
        else if (map[my_g-1][mx_g-1] == 1){
            userhealth = userhealth - monsterhitpoint;
            ((GlobalVariables) this.getApplication()).setCurrentHealth(userhealth);
            CheckUserHealth();
            drawBars();
        }
        //if student out of range goose move toward student
        else if ((my_g == my_s)&&((mx_s+1 == mx_g)||(mx_s-1 == mx_g))){
            direction = 2;
            my_g = my_g -1;
            y_g = y_g +85;
            //else gy+1
        }
        else if ((mx_g == mx_s)&&((my_s+1 == my_g)||(my_s-1 == my_g)))
        {
            direction =3;
            mx_g = mx_g -1;
            x_g = x_g -85;
            //else gx+1
        }
        else if (mx_s > mx_g){
            direction = 4;
                mx_g = mx_g +1;
                x_g = x_g +85;
        }
        else if (mx_s < mx_g){
                direction =3;
                mx_g = mx_g -1;
                x_g = x_g -85;

        }
        else if (my_s > my_g){
             direction =1;
                my_g = my_g +1;
                y_g = y_g -85;

        }
        else if (my_s < my_g){
            direction = 2;
                my_g = my_g -1;
                y_g = y_g +85;
        }

        ValidateLocation(direction);
        drawBars();
        DisplayGoose(); //update graphics
        UpdateGrid();
    }


    public void DisplayStudent()
    {
        final ImageView StudentV = (ImageView)findViewById(R.id.Student);
        StudentV.setX(x_s);
        StudentV.setY(y_s);
        StudentV.invalidate();


        final ImageView AS_R = (ImageView)findViewById(R.id.SAttackZoneR);
        AS_R.setX(x_s+85);
        AS_R.setY(y_s);
        AS_R.invalidate();

        final ImageView AS_L = (ImageView)findViewById(R.id.SAttackZoneL);
        AS_L.setX(x_s-85);
        AS_L.setY(y_s);
        AS_L.invalidate();

        final ImageView AS_U = (ImageView)findViewById(R.id.SAttackZoneU);
        AS_U.setX(x_s);
        AS_U.setY(y_s-85);
        AS_U.invalidate();

        final ImageView AS_D = (ImageView)findViewById(R.id.SAttackZoneD);
        AS_D.setX(x_s);
        AS_D.setY(y_s+85);
        AS_D.invalidate();

    }
    public void DisplayGoose()
    {
        final ImageView GooseV = (ImageView)findViewById(R.id.Goose);
        GooseV.setX(x_g);
        GooseV.setY(y_g);
        GooseV.invalidate();

        final ImageView AG_LU = (ImageView)findViewById(R.id.GAttackZoneLU);
        AG_LU.setX(x_g-85);
        AG_LU.setY(y_g-85);
        AG_LU.invalidate();

        final ImageView AG_RU = (ImageView)findViewById(R.id.GAttackZoneRU);
        AG_RU.setX(x_g+85);
        AG_RU.setY(y_g-85);
        AG_RU.invalidate();

        final ImageView AG_RD = (ImageView)findViewById(R.id.GAttackZoneRD);
        AG_RD.setX(x_g+85);
        AG_RD.setY(y_g+85);
        AG_RD.invalidate();

        final ImageView AG_LD = (ImageView)findViewById(R.id.GAttackZoneLD);
        AG_LD.setX(x_g-85);
        AG_LD.setY(y_g+85);
        AG_LD.invalidate();


    }
    public void DisplayBoulder()
    {
       // int y1 = abs((boulderlocation[0][1]-1)-28) ;
        final ImageView B1 = (ImageView)findViewById(R.id.Boulder1);
        B1.setX((boulderlocation[0][0]-1)*85);
        B1.setY(595-((boulderlocation[0][1]-1)*85));
        B1.invalidate();

        final ImageView B2 = (ImageView)findViewById(R.id.Boulder2);
        B2.setX((boulderlocation[1][0]-1)*85);
        B2.setY(595-((boulderlocation[1][1]-1)*85));
        B2.invalidate();

        final ImageView B3 = (ImageView)findViewById(R.id.Boulder3);
        B3.setX((boulderlocation[2][0]-1)*85);
        B3.setY(595-((boulderlocation[2][1]-1)*85));
        B3.invalidate();

        final ImageView B4 = (ImageView)findViewById(R.id.Boulder4);
        B4.setX((boulderlocation[3][0]-1)*85);
        B4.setY(595-((boulderlocation[3][1]-1)*85));
        B4.invalidate();

        final ImageView B5 = (ImageView)findViewById(R.id.Boulder5);
        B5.setX((boulderlocation[4][0]-1)*85);
        B5.setY(595-((boulderlocation[4][1]-1)*85));
        B5.invalidate();
    }

    public void CheckUserHealth()
    {
        if (userhealth <= 0){
            EndGameLoose();
        }
    }

    public void ValidateLocation(int direction)
    {
        if (direction == 1)//up
        {
            if ((map[my_g][mx_g] == 5)||(map[my_g][mx_g] == 1))
            {
                my_g = my_g -3;
                y_g = y_g +255;
            }
        }
        else if (direction == 2)//down
        {
            if ((map[my_g][mx_g] == 5)||(map[my_g][mx_g] == 1))
            {
                my_g = my_g +3;
                y_g = y_g -255;
            }
        }
        else if (direction == 3)//left
        {
            if ((map[my_g][mx_g] == 5)||(map[my_g][mx_g] == 1))
            {
                mx_g = mx_g +3;
                x_g = x_g +255;
            }
        }
        else if (direction == 4)//right
        {
            if ((map[my_g][mx_g] == 5)||(map[my_g][mx_g] == 1))
            {
                mx_g = mx_g -3;
                x_g = x_g -255;
            }
        }
    }
    public void ValidateLocationStudent(int direction)
    {
        if (direction == 1)//up
        {
            if ((map[my_s][mx_s] == 5)||(map[my_s][mx_s] == 3))
            {
                my_s = my_s - 3;
                y_s = y_s + 255;
            }
        }
        else if (direction == 2)//down
        {
            if ((map[my_s][mx_s] == 5)||(map[my_s][mx_s] == 3))
            {
                y_s = y_s - 255;
                my_s = my_s + 3;
            }
        }
        else if (direction == 3)//left
        {
            if ((map[my_s][mx_s] == 5)||(map[my_s][mx_s] == 3))
            {
                mx_s = mx_s + 3;
                x_s = x_s + 255;
            }
        }
        else if (direction == 4)//right
        {
            if ((map[my_s][mx_s] == 5)||(map[my_s][mx_s] == 3))
            {
                mx_s = mx_s - 3;
                x_s = x_s - 255;
            }
        }
    }


    public void ArrowUp(View view) {
        CheckUserHealth();
         if (energy >= movementcost )
        {
            my_s = my_s + 1;
            y_s = y_s - 85;
            value = 1;

            ValidateLocationStudent(value);

            energy = energy - movementcost;
            ((GlobalVariables) this.getApplication()).setCurrentEnergy(energy);
            drawBars();

            UpdateGrid();
            DisplayStudent();

        }
        GooseTurn();

    }
    public void ArrowDown(View view) {
        CheckUserHealth();
         if (energy >= movementcost )
        {
            my_s = my_s - 1;
            y_s = y_s + 85;
            value = 2;
            ValidateLocationStudent(value);

            energy = energy - movementcost;
            ((GlobalVariables) this.getApplication()).setCurrentEnergy(energy);
            drawBars();

            UpdateGrid();
            DisplayStudent();

        }
        GooseTurn();
    }
    public void ArrowLeft(View view) {
        CheckUserHealth();
        if (energy >= movementcost )
        {
            mx_s = mx_s - 1;
            x_s = x_s - 85;
            value = 3;

            ValidateLocationStudent(value);

            energy = energy - movementcost;
            ((GlobalVariables) this.getApplication()).setCurrentEnergy(energy);
            drawBars();

            UpdateGrid();
            DisplayStudent();

        }
        GooseTurn();
    }
    public void ArrowRight(View view) {
        CheckUserHealth();
        if (energy >= movementcost )
        {
            mx_s = mx_s + 1;
            x_s = x_s + 85;
            value = 4;

            ValidateLocationStudent(value);
            energy = energy - movementcost;
            ((GlobalVariables) this.getApplication()).setCurrentEnergy(energy);
            drawBars();

            UpdateGrid();
            DisplayStudent();

        }
        GooseTurn();
    }



    public void AttackMonster(View view) // Runs when user presses 'ATTACK' Button
    {
         if (((mx_s == mx_g)&&(my_s+1 == my_g))||((mx_s == mx_g)&&(my_s-1 == my_g)))
             //check if within x attack range
        {
            monsterhealth = monsterhealth - hitpoint;
            drawBars();
            if (monsterhealth <= 0)
            {
                EndGameWin();
            }
            else
            {
                GooseTurn();
            }
        }
         else if(((my_s == my_g)&&(mx_s+1 == mx_g))||((my_s == my_g)&&(mx_s-1 == mx_g)))
             //check if within y attack range
         {
             monsterhealth = monsterhealth - hitpoint;
             drawBars();

             if (monsterhealth <= 0)
             {
                 EndGameWin();
             }
             else
             {
                 GooseTurn();
             }
        }
    }

    public void HealUser(View view) // Runs when user presses 'HEAL'
    {

        userhealth = userhealth + healpoint;
        ((GlobalVariables) this.getApplication()).setCurrentHealth(userhealth);
        energy = energy - healenergycost;
        ((GlobalVariables) this.getApplication()).setCurrentEnergy(energy);
        drawBars();
        GooseTurn();
    }

    public void RechargeUser(View view) // Runs when user presses 'ENERGIZE'
    {
        energy = energy + rechargeamount;
        ((GlobalVariables) this.getApplication()).setCurrentEnergy(energy);
        drawBars();
        GooseTurn();
    }


    public void EndGameWin() // if user wins
    {
        if (this.monsterID != -1 && this.buildingID != -1) {
            ((GlobalVariables) this.getApplication()).defeatMonster(this.monsterID);
            ((GlobalVariables) this.getApplication()).removeBuilding(this.buildingID);
            ((GlobalVariables) this.getApplication()).randomizeMonsterLocations();
        }
        setContentView(R.layout.user_win_return);
    }

    public void EndGameLoose() // if user looses
    {
        resetStats();
        setContentView(R.layout.user_loose);
    }

    private void drawUserHealthBar()
    {
        // draw/update health and energy bars
        this.userHealthBar.drawBar(userhealth, userHealthCanvas);
    }

    private void drawUserEnergyBar()
    {
        // draw/update health and energy bars
        this.userEnergyBar.drawBar(energy, userEnergyCanvas);
    }

    private void drawMonsterBar()
    {
        // draw/update health and energy bars
        this.monsterHealthBar.drawBar(monsterhealth, monsterCanvas);
    }

    public void drawBars() {
        drawUserHealthBar();
        drawUserEnergyBar();
        drawMonsterBar();
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