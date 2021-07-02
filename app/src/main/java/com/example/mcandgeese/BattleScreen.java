package com.example.mcandgeese;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class BattleScreen extends AppCompatActivity {

    int userhealth = 100; //Remaining User Health (need to be set from previous state/battle)
    int energy = 100; //Remaining User Energy (need to be set from previous state/battle)
    int monsterhealth = 50; // Starting Monster Health
    int monsterhitpoint = 10; // Amount of Damage to user (per turn)
    int attackenergycost = 25; // Cost of energy per attack to monster
    int healenergycost = 5; //Cost of energy per heal to user
    int hitpoint = 10; // Amount of damage per attack to monster
    int healpoint = 15; // Amount user heals per heal
    int rechargeamount = 10; // Amount user energy increases per recharge
    //need to load in counter that is globally held

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_screen);

        //set starting stats
        TextView UH = findViewById(R.id.HealthCount);
        UH.setText(String.valueOf(userhealth));

        TextView MH = findViewById(R.id.MonsterHealthCount);
        MH.setText(String.valueOf(monsterhealth));

        TextView AB = findViewById(R.id.EnergyCount);
        AB.setText(String.valueOf(energy));

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


    }


    public void AttackMonster(View view) { // Runs when user presses 'ATTACK' Button
        if (energy >= attackenergycost)
        {
            monsterhealth = monsterhealth - hitpoint;
            energy = energy - attackenergycost;

            TextView UH = findViewById(R.id.HealthCount);
            UH.setText(String.valueOf(userhealth));

            TextView MH = findViewById(R.id.MonsterHealthCount);
            MH.setText(String.valueOf(monsterhealth));

            TextView GH = findViewById(R.id.EnergyCount);
            GH.setText(String.valueOf(energy));

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
            energy = energy - healenergycost;

            TextView UH = findViewById(R.id.HealthCount);
            UH.setText(String.valueOf(userhealth));

            TextView MH = findViewById(R.id.MonsterHealthCount);
            MH.setText(String.valueOf(monsterhealth));

            TextView EC = findViewById(R.id.EnergyCount);
            EC.setText(String.valueOf(energy));

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

        TextView UH = findViewById(R.id.HealthCount);
        UH.setText(String.valueOf(userhealth));

        TextView MH = findViewById(R.id.MonsterHealthCount);
        MH.setText(String.valueOf(monsterhealth));

        TextView EC = findViewById(R.id.EnergyCount);
        EC.setText(String.valueOf(energy));

        MonsterTurn();

    }


    public void MonsterTurn() // Runs after any button is pressed, This is the monsters attack
    {
        TextView textView = findViewById(R.id.textView);
        textView.setText("Monster Turn!");



        userhealth = userhealth - monsterhitpoint ;

            TextView UH = findViewById(R.id.HealthCount);
            UH.setText(String.valueOf(userhealth));

            TextView MH = findViewById(R.id.MonsterHealthCount);
            MH.setText(String.valueOf(monsterhealth));

            TextView EC = findViewById(R.id.EnergyCount);
            EC.setText(String.valueOf(energy));

        if (userhealth <= 0) { EndGameLoose(); }

        textView.setText("User Turn!");

    }

    public void EndGameWin() // if user wins
    {
        //Need to import counter for number of remaining monsters and if counter ==0 then user has completed game
        //setContentView(R.layout.user_win_final

        setContentView(R.layout.user_win_return);


    }

    public void EndGameLoose() // if user looses
    {
        setContentView(R.layout.user_loose);
    }



}