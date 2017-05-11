package com.dahlinghaus.brian.bossfight.helpers;

import java.util.Random;

/**
 * Created by edahlinghaus on 5/10/17.
 */

public class BattleRand {
    private static Random rand = new Random();

    private void resetRandom() {
        rand = new Random();
    }

    public int d(int n) {
        resetRandom();
        return rand.nextInt(n)+1;
    }

    public int between(int min, int max) {
        resetRandom();
        int diff = max-min;
        if( diff <= 0 ) {
            return 1;
        } else {
            return d(diff) + min;
        }
    }
}
