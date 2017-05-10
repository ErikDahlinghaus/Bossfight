package com.dahlinghaus.brian.bossfight.models;

import java.util.Random;

/**
 * Created by edahlinghaus on 5/10/17.
 */

public class Player {

    int str = 5, dex = 5, vit = 5, evasion = 5;
    int exp = 0;

    Random randAttack = new Random();

    String name = "Player";

    public void gainExp() {
        exp += 15;
    }

    public int getExp() {
        return exp;
    }
    public int attack(){
        return str *2;
    }
    private int damageCalc(){
        return str *2;
    }
    public int getStrength() {
        return str;
    }
    public int modStrength(int n) {
        return str += n;
    }
    public Player() {

    }
    static private Player thePlayer = null;
    static public Player getPlayerInstance() {
        if(thePlayer == null) {
            thePlayer = new Player();
        }
        return thePlayer;

    }
}
