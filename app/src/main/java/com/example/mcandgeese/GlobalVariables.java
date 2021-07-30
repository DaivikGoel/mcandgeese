package com.example.mcandgeese;

import android.app.Application;

import java.util.HashSet;

public class GlobalVariables extends Application {
    public int currentHealth = 100;
    public int currentEnergy = 100;
    public int currentLocationX = 0;
    public int currentLocationY = 0;
    public HashSet<String> clearedBuildings = new HashSet<>();
    public HashSet<Integer> clearedMonsters = new HashSet<>();

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

    public void addClearedBuilding(String location){ this.clearedBuildings.add(location); }

    public HashSet<Integer> getClearedMonsters(){
        return new HashSet<>(this.clearedMonsters);
    }

    public void addClearedMonsters(Integer monsterID) {
        this.clearedMonsters.add(monsterID);
    }

    public void resetClearedLocations() { clearedBuildings.clear(); }

    public int getCurrentLocationX() { return this.currentLocationX; }

    public int getCurrentLocationY() { return this.currentLocationY; }

    public void setCurrentLocationX(int locX) { this.currentLocationX = locX; }

    public void setCurrentLocationY(int locY) { this.currentLocationY = locY; }
}
