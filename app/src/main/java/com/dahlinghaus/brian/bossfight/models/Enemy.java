package com.dahlinghaus.brian.bossfight.models;

import com.dahlinghaus.brian.bossfight.exceptions.EnemyValidationException;
import com.dahlinghaus.brian.bossfight.helpers.BattleRand;

/**
 * Created by edahlinghaus on 5/10/17.
 */

public class Enemy implements IFightable {
    private static final BattleRand rand = new BattleRand();

    // Stats defined in enemies.csv
    private String name, res_name;
    private int
            min_hp, max_hp,
            evasion, accuracy,
            min_attack, max_attack,
            min_exp, max_exp;

    // Things about current enemy
    private int current_hp;

    private int getInitialHealthValue() {
        return rand.between(min_hp, max_hp);
    }

    // Setup basic enemy (for use in constructor)
    private void setup() {
        name = "invalid";
        res_name = "invalid";;
        min_hp = 1;
        max_hp = 1;
        min_exp = 1;
        max_exp = 1;
        min_attack = 1;
        max_attack = 1;
        evasion = 1;
        accuracy = 1;
        current_hp = getInitialHealthValue();
    }

    // Validate string array and load object from string array (for use in constructor)
    private static final int STRING_ARRAY_LENGTH = 10;
    private void setup(String[] s) throws EnemyValidationException {
        // Validate String Array
        // Error out if string is the wrong length
        if (s.length < STRING_ARRAY_LENGTH) {
            throw new EnemyValidationException("String array does not represent a valid");
        }

        // Load values from string array, template from: /app/source/main/assets/enemies.csv
        //
        // name, res_name, min_hp, max_hp, min_exp, max_exp, min_attack, max_attack, evasion, accuracy
        //
        name = s[0];
        res_name = s[1];
        min_hp = Integer.parseInt(s[2]);
        max_hp = Integer.parseInt(s[3]);
        min_exp = Integer.parseInt(s[4]);
        max_exp = Integer.parseInt(s[5]);
        min_attack = Integer.parseInt(s[6]);
        max_attack = Integer.parseInt(s[7]);
        evasion = Integer.parseInt(s[8]);
        accuracy = Integer.parseInt(s[9]);
        current_hp = getInitialHealthValue();
    }

    // default constructor gives you blank enemy
    public Enemy() { setup(); }

    // constructor from a string array (csv)
    public Enemy(String[] s) throws EnemyValidationException { setup(s); }

    // Constructor to clone an Enemy
    public Enemy(Enemy e) {
        this.name = e.getName();
        this.res_name = e.getResName();
        this.min_hp = e.getMinHP();
        this.max_hp = e.getMaxHP();
        this.min_exp = e.getMinEXP();
        this.max_exp = e.getMaxEXP();
        this.min_attack = e.getMinAttack();
        this.max_attack = e.getMaxAttack();
        this.evasion = e.getEvasion();
        this.accuracy = e.getAccuracy();
        current_hp = getInitialHealthValue();
    }

    // Method to clone an Enemy
    public Enemy clone() {
        return new Enemy(this);
    }

    // getters, setters, info about object
    public String getName() {
        return name;
    }
    public String getResName() { return res_name; }
    public int getCurrentHP() {
        return current_hp;
    }
    public int getMinHP() { return min_hp; }
    public int getMaxHP() { return max_hp; }
    public int getEvasion() { return evasion; }
    public int getAccuracy() { return accuracy; }
    public int getMinAttack() { return min_attack; }
    public int getMaxAttack() { return max_attack; }
    public int getMinEXP() { return min_exp; }
    public int getMaxEXP() { return max_exp; }
    public int gainExp() { return rand.between(min_exp, max_exp); }
    public boolean isDead() { return (current_hp <= 0) ? true : false; }

    // Do damage to enemy
    // returns true if attack it
    public boolean doDamage(Player p) {
        if( isHit(p) ) {
            // do damage to the current enemy,
            //  we pass the current enemy, represented by this, to the player to evalute it's attack strength against us
            //  then we deduct this value returned from the player from our current_hp
            current_hp -= p.attack(this);
            // prevent current_hp going lower than 0
            current_hp = (current_hp < 0) ? 0 : current_hp;
            return true;
        } else {
            return false;
        }
    }

    public boolean isHit(Player p) {
        if( accuracy > p.getEvasion() ) {
            return true;
        } else {
            return rand.d(8) > 5;
        }
    }

    // attack damage
    public int attack(Player p) {
        return rand.between(min_attack, max_attack);
    }

}
