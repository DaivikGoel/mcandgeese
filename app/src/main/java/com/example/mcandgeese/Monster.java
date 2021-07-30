package com.example.mcandgeese;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class Monster extends AppCompatActivity {
    public String monsterName;
    public int monsterHealth;
    public int monsterHitPoints;
    public int monsterID;

    Map<Integer, String> monsters = new HashMap<Integer, String>() {{
        put(1, "Goose");
        put(2, "Peter Levine");
    }};
    public List<String> monsterQuotes = new ArrayList<>(Arrays.asList("Wow would you look at that, it's an ECE student", "ECE students must be punished for what they have done to us"));

    // TODO: Have to make the global variables remember which monsters are dead. For some reason i get null pointer exceptions when referencing global variable sets.

    // generates a monster
    public Monster() {
        HashMap<Integer, String> tempMonsters = new HashMap<>(monsters);
        //HashSet<Integer> visited = ((GlobalVariables) this.getApplication()).clearedMonsters;
        HashSet<Integer> visited = new HashSet<>();
        for (Integer i: visited) {
            tempMonsters.remove(i);
        }
        Random rand = new Random();
        int upper = tempMonsters.size();
        int randomInt = rand.nextInt(upper);
        if (upper == 0) {
            this.monsterName = "A ghost of a former enemy lies here";
            this.monsterID = 0;
            this.monsterHealth = 1;
            this.monsterHitPoints = 1;
            return;
        }
        int iterator = 0;
        for (Integer i : tempMonsters.keySet()) {
            if (iterator == randomInt) {
                this.monsterID = i;
                this.monsterName = tempMonsters.get(i);
                this.monsterHealth = new Random().nextInt(100);
                this.monsterHitPoints = new Random().nextInt(20);
                //((GlobalVariables) this.getApplication()).addClearedMonsters(i);
                return;
            }
            iterator++;
        }
        this.monsterID = -1;
        this.monsterName = "A mysterious being prowls in the night";
        this.monsterHealth = new Random().nextInt(200);
        this.monsterHitPoints = new Random().nextInt(69);
    }

    public int getMonsterHealth() {
        return this.monsterHealth;
    }

    public int getMonsterHitPoints() {
        return this.monsterHitPoints;
    }

    public int getMonsterID() {
        return this.monsterID;
    }

    public String getMonsterName() {
        return this.monsterName;
    }

}
