package com.condor.customersmanager.util;

import com.condor.customersmanager.dto.rest.CustomerRequest;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.api.Randomizer;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RandomFactory {

    public static EasyRandom getEasyRandom () {
        EasyRandomParameters easyRandomParameters = new EasyRandomParameters().randomize(
                field -> field.getName().equals("identification")
                        && field.getDeclaringClass().equals(CustomerRequest.class),
                new UniqueNumericStringRandomizer(10) // longitud fija de 10 dígitos
        );;
        easyRandomParameters.setStringLengthRange(new EasyRandomParameters.Range<>(1, 20));

        return new EasyRandom(easyRandomParameters);
    }

    private static  class UniqueNumericStringRandomizer implements Randomizer<String> {
        private final int length;
        private final Random random = new Random();
        private final Set<String> generated = new HashSet<>();

        public UniqueNumericStringRandomizer(int length) {
            this.length = length;
        }

        @Override
        public String getRandomValue() {
            String value;
            do {
                StringBuilder sb = new StringBuilder(length);
                for (int i = 0; i < length; i++) {
                    sb.append(random.nextInt(10));
                }
                value = sb.toString();
            } while (!generated.add(value)); // vuelve a generar si ya existe
            return value;
        }
    }
}
