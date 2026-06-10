package com.condor.transactionsmanager;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

public class RandomFactory {

    public static EasyRandom getEasyRandom () {
        EasyRandomParameters easyRandomParameters = new EasyRandomParameters();
        easyRandomParameters.setStringLengthRange(new EasyRandomParameters.Range<>(1, 20));

        return new EasyRandom(easyRandomParameters);
    }
}
