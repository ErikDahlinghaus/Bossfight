package com.dahlinghaus.brian.bossfight.models;

import com.dahlinghaus.brian.bossfight.helpers.BattleRand;
import com.dahlinghaus.brian.bossfight.helpers.Logger;

import java.util.Random;

/**
 * Created by edahlinghaus on 5/10/17.
 */

public class Player implements IFightable {

    // Setup helpers
    private static final BattleRand rand = new BattleRand();

    // Implement the singleton so there will only ever be once instance of Player
    private static Player thePlayer = null;
    public static synchronized Player getInstance() {
        if(thePlayer == null) {
            thePlayer = new Player();
        }
        return thePlayer;
    }

    // Constructor which prints an error if more than one player is created
    // TODO: Throw an exception instead
    public Player() {
        if(thePlayer != null) {
            Logger.error("Can not make more than one player");
        }
    }

    // Attributes
    private String name = "Player";
    private int
            str = 10,
            agi = 5,
            vit = 5,
            intel = 5,
            dex = 5,
            evasion = 5,
            exp = 0;

    // Current state of player
    private int
            health = 0,
            lastDamageDealt = 0;

    // getters, setters, info about object
    public String getName() { return name; }
    public int getExp() {
        return exp;
    }
    public int getStrength() {
        return str;
    }
    public int getLastDamageDealt() { return lastDamageDealt; }
    public int getEvasion() { return evasion; }

    // Get attack strength on enemy
    public int attack(Enemy e) {
        // calc damage vs enemy e
        int damage = rand.between(str-2, str+2) * 2;

        // save last damage dealt for printing messages and such
        lastDamageDealt = damage;

        // return damage
        return damage;
    }

    // Gain exp based on mob
    // returns the number of XP received for convenience
    public int gainExp(Enemy e) {
        int exp_gain = 5+e.getExp();
        exp += exp_gain;
        return exp_gain;
    }
}
