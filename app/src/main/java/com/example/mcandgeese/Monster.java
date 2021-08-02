package com.example.mcandgeese;

import android.provider.Settings;

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

    // Ghost of Goose: -1
    // Levine: 1
    // Goose: 2
    // Arriba: 3

    // generates a monster
    public Monster(String monsterName, int monsterHealth, int monsterHitPoints, int monsterID) {
        this.monsterName = monsterName;
        this.monsterHealth = monsterHealth;
        this.monsterHitPoints = monsterHitPoints;
        this.monsterID = monsterID;
    }

    public static Monster getLevine() {
        int monsterHitPoints = 15;
        int monsterHealth = 150;
        String monsterName = "Peter Levine";
        int monsterID = 1;
        return new Monster(monsterName, monsterHealth, monsterHitPoints, monsterID);
    }

    public static Monster getGoose() {
        int monsterHitPoints = 15;
        int monsterHealth = 150;
        String monsterName = "Menacing Goose";
        int monsterID = 2;
        return new Monster(monsterName, monsterHealth, monsterHitPoints, monsterID);
    }

    public static Monster getAmoeba() {
        int monsterHitPoints = 10;
        int monsterHealth = 150;
        String monsterName = "Remants of Arriba";
        int monsterID = 3;
        return new Monster(monsterName, monsterHealth, monsterHitPoints, monsterID);
    }

    public static Monster getKing() {
        int monsterHitPoints = 20;
        int monsterHealth = 150;
        String monsterName = "King Warrior";
        int monsterID = 4;
        return new Monster(monsterName, monsterHealth, monsterHitPoints, monsterID);
    }

    public static Monster getPink() {
        int monsterHitPoints = 20;
        int monsterHealth = 150;
        String monsterName = "Lord of Pink";
        int monsterID = 5;
        return new Monster(monsterName, monsterHealth, monsterHitPoints, monsterID);
    }

    public static Monster getGhost() {
        int monsterHitPoints = 1;
        int monsterHealth = 10;
        String monsterName = "Ghost of Goose";
        int monsterID = -1;
        return new Monster(monsterName, monsterHealth, monsterHitPoints, monsterID);
    }

    public static Monster getMonsterFromID(int monsterID) {
        switch(monsterID){
            case(1):
                return getLevine();
            case(2):
                return getGoose();
            case(3):
                return getAmoeba();
            case(4):
                return getKing();
            case(5):
                return getPink();
            default:
                return getGhost();
        }
    }

    public static Item getItemFromID(int monsterID){
        switch (monsterID){
            case(1):
                return Item.getWoodPiece();
            case(2):
                return Item.getGooseFeather();
            case(3):
                return Item.getAmoeba();
            case(4):
                return Item.getLionTooth();
            case(5):
                return Item.getPinkBracelet();
            case(6):
                return Item.getBronzePiece();
        }
        return null;
    }

    public static int getBuildingFromString(String building) {
        switch(building) {
            case("E5"):
                return 1;
            case("E7"):
                return 2;
            case("SLC"):
                return 3;
            case("DP"):
                return 4;
            case("QNC"):
                return 5;
            default:
                return -1;
        }
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
