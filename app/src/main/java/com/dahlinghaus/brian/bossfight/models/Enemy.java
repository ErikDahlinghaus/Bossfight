package com.dahlinghaus.brian.bossfight.models;

import java.util.Random;

/**
 * Created by edahlinghaus on 5/10/17.
 */

public class Enemy {
    String name;
    String[] enemies = {"Skeleton King", "Dragon Knight", "Imp Boss"};
    int minHealth = 200;
    int evasion, accuracy;
    Random randHealth = new Random();
    Random randEnemy = new Random();
    Random randAttack = new Random();
    int MaxAttack = 15;
    BattleHelper bhelper;
    public int health = randHealth.nextInt(minHealth);


    public Enemy() {
        name = enemies[randEnemy.nextInt(enemies.length)];
    }

    public String getName() {
        return this.name;
    }

    public void doDamage(int damage) {
        health -= damage;
    }

    public int getHealth() {
        return health;
    }

    public boolean isHit(Player e) {
        if (accuracy > e.evasion) {
            return true;
        } else {
            if (bhelper.randBetween(1, 8) > 5) {
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean isDead() {
        if (health <= 0)
            return true;
        else
            return false;
    }

    public int attack() {
        return randAttack.nextInt((MaxAttack) + 5);
    }

    private class BattleHelper {

        public BattleHelper() {

        }

        public int randBetween(int a, int b) {
            Random rand = new Random();
            return (rand.nextInt(b) + a);
        }

        public int rollDie(int a) {
            Random rand = new Random();
            return (rand.nextInt(a) + 1);
        }

    }
}
