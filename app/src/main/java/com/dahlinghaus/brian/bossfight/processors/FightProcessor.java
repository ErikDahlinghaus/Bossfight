package com.dahlinghaus.brian.bossfight.processors;

import com.dahlinghaus.brian.bossfight.models.IFightable;

/**
 * Created by edahlinghaus on 5/10/17.
 */

public class FightProcessor {
    // Thinking about extracting the fight sequencing here

    IFightable p1, p2;

    public FightProcessor(IFightable p1, IFightable p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public class FightResult {
        public FightResult() {}
    }

    public FightResult execute() {
        return new FightResult();
    }
}
