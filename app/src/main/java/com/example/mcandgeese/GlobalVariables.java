package com.example.mcandgeese;

import android.app.Application;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class GlobalVariables extends Application {
    public int currentHealth = 100;
    public int currentEnergy = 100;
    public int currentLocationX = 1115;
    public int currentLocationY = 400;
    public List<Integer> remainingBuildings;
    public List<Integer> remainingMonsters;
    public HashMap<Integer, Integer> buildingToMonster = new HashMap<>();
    public HashSet<Integer> occupiedBuilding;

    // Monsters
    // Peter Levine: 1
    // Goose: 2

    // Buildings
    // e5: 1
    // e7: 2
    // slc: 3
    // pac: 4
    // quantum: 5

    public void initializeVariables() {
        this.occupiedBuilding = new HashSet<>();
        this.remainingBuildings = new LinkedList<>();
        this.remainingBuildings.add(1);
        this.remainingBuildings.add(2);
        this.remainingBuildings.add(3);
        this.remainingBuildings.add(4);
        this.remainingBuildings.add(5);
        this.remainingMonsters = new LinkedList<>();
        this.remainingMonsters.add(1);
        this.remainingMonsters.add(2);
        randomizeMonsterLocations();
    }

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

    public int getCurrentLocationX() { return this.currentLocationX; }

    public int getCurrentLocationY() { return this.currentLocationY; }

    public void setCurrentLocation(int locX, int locY) {
        this.currentLocationX = locX;
        this.currentLocationY = locY;
    }

    public HashMap<Integer, Integer> getBuildingToMonster() {
        return this.buildingToMonster;
    }

    public void defeatMonster(int monsterID) {
        this.remainingMonsters.remove(Integer.valueOf(monsterID));
    }

    public void removeBuilding(int buildingID) {
        this.remainingBuildings.remove(Integer.valueOf(buildingID));
    }

    public void randomizeMonsterLocations() {
        this.buildingToMonster.clear();
        this.occupiedBuilding.clear();
        int buildingNum = this.remainingBuildings.size();
        Random rand = new Random();
        for (Integer remainingMonster : this.remainingMonsters) {
            int randomBuilding = rand.nextInt(buildingNum);
            while (this.occupiedBuilding.contains(randomBuilding) || !this.remainingBuildings.contains(randomBuilding)) {
                randomBuilding = rand.nextInt(buildingNum);
            }
            this.buildingToMonster.put(randomBuilding, remainingMonster);
            this.occupiedBuilding.add(randomBuilding);
        }
    }
}
