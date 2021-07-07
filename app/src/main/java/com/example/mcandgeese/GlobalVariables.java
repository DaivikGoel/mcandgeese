package com.example.mcandgeese;

import android.app.Application;

public class GlobalVariables extends Application {
    public int currentHealth = 100;
    public int currentEnergy = 100;
    public int currentLocationX = 0;
    public int currentLocationY = 0;

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
}
