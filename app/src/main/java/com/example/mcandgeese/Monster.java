package com.example.mcandgeese;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Monster extends AppCompatActivity {
    public String monsterName;
    public int monsterHealth;
    public int monsterHitPoints;

    public Set<String> monsters = new HashSet<>(Arrays.asList("Goose", "Peter-Levine"));
    public List<String> monsterQuotes = new ArrayList<>(Arrays.asList("Wow would you look at that, it's an ECE student", "ECE students must be punished for what they have done to us"));

    // generates a monster
    public Monster() {
        Set<String> tempMonsters = new HashSet<>(monsters);
        Set<String> visited = ((GlobalVariables) this.getApplication()).getClearedMonsters();
        for (String s : visited) {
            tempMonsters.remove(s);
        }
        Random rand = new Random();
        int upper = tempMonsters.size();
        int randomInt = rand.nextInt(upper);
        if (upper == 0) {
            this.monsterName = "A ghost of a former enemy lies here";
            this.monsterHealth = 1;
            this.monsterHitPoints = 1;
            return;
        }
        int iterator = 0;
        for (String s : tempMonsters) {
            if (iterator == randomInt) {
                this.monsterName = s;
                this.monsterHealth = new Random().nextInt(100);
                this.monsterHitPoints = new Random().nextInt(20);
                ((GlobalVariables) this.getApplication()).addClearedMonsters(s);
                return;
            }
            iterator++;
        }

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

    public String getMonsterName() {
        return this.monsterName;
    }

}
