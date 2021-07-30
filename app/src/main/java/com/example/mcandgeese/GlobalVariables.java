package com.example.mcandgeese;

import android.app.Application;

import java.util.HashSet;

public class GlobalVariables extends Application {
    public int currentHealth = 100;
    public int currentEnergy = 100;
    public int currentLocationX = 0;
    public int currentLocationY = 0;
    public HashSet<String> clearedBuildings = new HashSet<>();
    public HashSet<String> clearedMonsters = new HashSet<>();

    public int getCurrentHealth() {
        return this.currentHealth;
    }

    public int getCurrentEnergy() {
        return this.currentEnergy;
    }

    public void setCurrentHealth(int newHealth) {
        this.currentHealth = newHealth;
    }

    public void setCurrentEnergy(int newEnergy) {
        this.currentEnergy = newEnergy;
    }

    public void addClearedBuilding(String location){ clearedBuildings.add(location); }

    public HashSet<String> getClearedMonsters(){
        return this.clearedMonsters;
    }

    public void addClearedMonsters(String monsterName) {
        clearedMonsters.add(monsterName);
    }

    public void resetClearedLocations() { clearedBuildings.clear(); }
}
