package com.example.mcandgeese;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mcandgeese.gamePanel.MonsterHealthBar;
import com.example.mcandgeese.gamePanel.UserHealthBar;

import java.util.Arrays;

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



    int x_s = 605;
    int y_s = 400;

    int x_g = 1710;
    int y_g = 400;

    int[][] map = new int[10][28];
    String forprint1,forprint2,forprint3,forprint4,forprint5,forprint6,forprint7,forprint8,forprint9,forprint10;
    int mx_s = 8;
    int my_s = 4;

    int mx_g = 21;
    int my_g = 4;

    int value =0;




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
        onWindowFocusChanged(true);
        setupmap();
        startlocations();


  //      for(int i = 0; i < 10; i++)
            for (int j =0; j <28;j++) {

                forprint1 = forprint1 + " " + map[0][j];
                forprint2 = forprint2 + " " + map[1][j];
                forprint3 = forprint3 + " " + map[2][j];
                forprint4 = forprint4 + " " + map[3][j];
                forprint5 = forprint5 + " " + map[4][j];
                forprint6 = forprint6 + " " + map[5][j];
                forprint7 = forprint7 + " " + map[6][j];
                forprint8 = forprint8 + " " + map[7][j];
                forprint9 = forprint9 + " " + map[8][j];
                forprint10 = forprint10 + " " + map[9][j];
            }

        TextView row0 = findViewById(R.id.textView9);
        row0.setText(forprint1);
        TextView row1 = findViewById(R.id.textView20);
        row1.setText(forprint2);
        TextView row2 = findViewById(R.id.textView21);
        row2.setText(forprint3);
        TextView row3 = findViewById(R.id.textView22);
        row3.setText(forprint4);
        TextView row4 = findViewById(R.id.textView23);
        row4.setText(forprint5);
        TextView row5 = findViewById(R.id.textView24);
        row5.setText(forprint6);
        TextView row6 = findViewById(R.id.textView25);
        row6.setText(forprint7);
        TextView row7 = findViewById(R.id.textView26);
        row7.setText(forprint8);
        TextView row8 = findViewById(R.id.textView27);
        row8.setText(forprint9);
        TextView row9 = findViewById(R.id.textView28);
        row9.setText(forprint10);

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

    public void setupmap(){

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
    }

    public void startlocations(){
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

    public void UpdateGrid(){
      //  Arrays.fill(map, 0); pretty sure issue. still need to clear array tho
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
        setupmap();

    }

    /*


    public void CheckMap(int value, int g_s){
        if (g_s == 0) //student
        {
            if (value ==0){

            }
            else if (value == 3) {

            }
            else if (value == 4) {

            }
            else if (value == 5) { //Blocked


            }
        }
        else if (g_s == 1) // goose
        {
            if (value ==0){

            }
            else if (value == 1){

            }
            else if (value == 2){

            }
            else if (value == 3) {

            }
            else if (value == 4) {

            }
            else if (value == 5) {

            }
        }

    }
*/

    public void GooseTurn(){
        //goose move toward student
        if (mx_s > mx_g){
            mx_g = mx_g +1;
            x_g = x_g +85;
        }
        else if (mx_s < mx_g){
            mx_g = mx_g -1;
            x_g = x_g -85;
        }
        else if (my_s > my_g){
            my_g = my_g +1;
            y_g = y_g -85;
        }
        else if (my_s < my_g){
            my_g = my_g -1;
            y_g = y_g +85;
        }

        //if student within range -student_health
        if (map[my_g+1][mx_g+1] == 1){
            //-student health
        }
        else if (map[my_g-1][mx_g+1] == 1){
            //-student health
        }
        else if (map[my_g+1][mx_g-1] == 1){
            //-student health
        }
        else if (map[my_g-1][mx_g-1] == 1){
            //-student health
        }

        DisplayGoose(); //update graphics
    }

    public void DisplayStudent(){ //add in range graphics and make similar for DisplayGoose()
        final ImageView StudentV = (ImageView)findViewById(R.id.Student);
        StudentV.setX(x_s);
        StudentV.setY(y_s);
        StudentV.invalidate();

        /*
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
         */
    }

    public void DisplayGoose(){
        final ImageView GooseV = (ImageView)findViewById(R.id.Goose);
        GooseV.setX(x_g);
        GooseV.setY(y_g);
        GooseV.invalidate();
/*
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
*/

    }



    public void ArrowUp(View view) {

        my_s = my_s +1;
        value = map[my_s][mx_s];

        if (value == 0){ //free
            y_s = y_s -85;
        }
        else if (value == 2){ //student range
            y_s = y_s -85;
        }
        else if (value == 4){ //goose range
            y_s = y_s -85;
        }
        else{
            my_s = my_s -1;
        }

      //  UpdateGrid();
        DisplayStudent();
        GooseTurn();

    }
    public void ArrowDown(View view) {
        my_s = my_s -1;
        value = map[my_s][mx_s];

        if (value == 0){ //free
            y_s = y_s +85;
        }
        else if (value == 2){ //student range
            y_s = y_s +85;
        }
        else if (value == 4){ //goose range
            y_s = y_s +85;
        }
        else{
            my_s = my_s +1;
        }

      //  UpdateGrid();
        DisplayStudent();
        GooseTurn();
    }
    public void ArrowLeft(View view) {
        mx_s = mx_s -1;
        value = map[my_s][mx_s];

        if (value == 0){ //free
            x_s = x_s -85;
        }
        else if (value == 2){ //student range
            x_s = x_s -85;
        }
        else if (value == 4){ //goose range
            x_s = x_s -85;
        }
        else{
            mx_s = mx_s +1;
        }

       // UpdateGrid();
        DisplayStudent();
        GooseTurn();
    }
    public void ArrowRight(View view) {
        mx_s = mx_s +1;
        value = map[my_s][mx_s];

        if (value == 0){ //free
            x_s = x_s +85;
        }
        else if (value == 2){ //student range
            x_s = x_s +85;
        }
        else if (value == 4){ //goose range
            x_s = x_s +85;
        }
        else{
            mx_s = mx_s -1;
        }

     //   UpdateGrid();
        DisplayStudent();
        GooseTurn();

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