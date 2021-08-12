package com.example.mcandgeese;

import android.app.Application;
import android.os.Build;
import android.provider.Settings;

import androidx.annotation.RequiresApi;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/*
    Implements Singleton Design pattern to ensure one source of truth
 */
public class GlobalVariables implements Serializable {
    private static final long serialVersionUID = 4750425879800330763L;
    private static GlobalVariables instance = null;
    public int currentHealth;
    public int currentEnergy;
    public int currentLocationX;
    public int currentLocationY;
    public List<Integer> remainingBuildings;
    public List<Integer> remainingMonsters;
    public HashMap<Integer, Integer> buildingToMonster;
    public HashSet<Integer> occupiedBuilding;
    public List<Item> items;

    // game started flag is used to indicate if we should save state and give user option to
    // continue previous game - we only want to do this if they have started a game
    public boolean gameStarted;

    protected GlobalVariables(){
        // protected constructor
        initializeVariables();
    }

    public static GlobalVariables getInstance() {
        if (instance == null){
            instance = new GlobalVariables();
        }
        return instance;
    }

    // Monsters
    // Peter Levine: 1
    // Goose: 2
    // Arriba: 3
    // King: 4
    // Pink: 5

    // Buildings
    // e5: 1
    // e7: 2
    // slc: 3
    // dp: 4
    // quantum: 5

    public void initializeVariables() {
        this.items = new ArrayList<>();
        this.currentHealth = 100;
        this.currentEnergy = 100;
        this.currentLocationX = 1115;
        this.currentLocationY = 400;
        this.gameStarted = false;
        this.buildingToMonster = new HashMap<>();
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
        this.remainingMonsters.add(3);
        this.remainingMonsters.add(4);
        this.remainingMonsters.add(5);
        randomizeMonsterLocations();
    }

    public boolean getGameStarted() { return this.gameStarted; }

    public void setGameStarted(boolean gameStarted) { this.gameStarted = gameStarted; }

    public List<Item> getItems() { return this.items; }

    public void setItems(List<Item> items) { this.items = items; }

    public void addItem(Item item) {
        boolean itemExists = false;
        for (Item existingItem : items){
            if (existingItem.getName() == item.getName()){
                existingItem.setQuantity(existingItem.getQuantity()+item.getQuantity());
                itemExists = true;
            }
        }
        if (!itemExists){
            items.add(item);
        }
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
        addItem(Monster.getItemFromID(monsterID));
        int[] watcardPoints = new int[]{0,0,25,50,100,0,75,25,0};
        Random randomizer = new Random();
        int points = watcardPoints[randomizer.nextInt(watcardPoints.length)];
        if (points > 0){
            Item watcard = Item.getWatCard();
            watcard.setQuantity(points);
            addItem(watcard);
        }
    }

    public void removeBuilding(int buildingID) {
        this.remainingBuildings.remove(Integer.valueOf(buildingID));
    }

    public boolean noMonstersRemain() {
        if (this.remainingMonsters.size() == 0) {
            return true;
        }
        return false;
    }

    public void randomizeMonsterLocations() {
        this.buildingToMonster.clear();
        this.occupiedBuilding.clear();
        int buildingNum = 6;
        Random rand = new Random();
        for (Integer remainingMonster : this.remainingMonsters) {
            int randomBuilding = rand.nextInt(buildingNum);
            while (this.occupiedBuilding.contains(Integer.valueOf(randomBuilding)) || !this.remainingBuildings.contains(Integer.valueOf(randomBuilding))) {
                randomBuilding = rand.nextInt(buildingNum);
            }
            this.buildingToMonster.put(randomBuilding, remainingMonster);
            this.occupiedBuilding.add(randomBuilding);
        }
    }

    /** Read the object from Base64 string. */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public String fromString(String s) throws IOException , ClassNotFoundException {
        byte [] data = Base64.getDecoder().decode( s );
        ObjectInputStream ois = new ObjectInputStream(
                new ByteArrayInputStream(  data ) );
        GlobalVariables o  = (GlobalVariables)ois.readObject();
        ois.close();
        this.buildingToMonster = o.buildingToMonster;
        this.currentLocationY = o.currentLocationY;
        this.currentLocationX = o.currentLocationX;
        this.currentHealth = o.currentHealth;
        this.currentEnergy = o.currentEnergy;
        this.remainingBuildings = o.remainingBuildings;
        this.remainingMonsters = o.remainingMonsters;
        this.buildingToMonster = o.buildingToMonster;
        this.occupiedBuilding = o.occupiedBuilding;
        this.gameStarted = o.gameStarted;
        this.items = o.items;

        // return statement used for debugging to make sure things are working
        return "Items:" + o.items + "Current Health" + o.currentHealth;
    }

    /** Write the object to a Base64 string. */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public String toString(String dummyString) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream( baos );
        oos.writeObject( this );
        oos.close();
        return Base64.getEncoder().encodeToString(baos.toByteArray());
    }
}
