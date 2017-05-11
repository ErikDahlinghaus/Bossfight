package com.dahlinghaus.brian.bossfight.helpers;

import java.util.Random;

/**
 * Created by edahlinghaus on 5/10/17.
 */

public class BattleRand {
    private final Random rand = new Random();

    public int d(int n) {
        return rand.nextInt(n)+1;
    }

    public int between(int min, int max) {
        int diff = max-min;
        if( diff <= 0 ) {
            return 1;
        } else {
            return d(diff) + min;
        }
    }
}
