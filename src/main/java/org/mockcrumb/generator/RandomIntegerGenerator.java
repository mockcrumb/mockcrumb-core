package org.mockcrumb.generator;

import java.util.Random;

public class RandomIntegerGenerator implements Generator {
    private static final Random RANDOM = new Random();

    @Override
    public Integer generate() {
        return RANDOM.nextInt();
    }
}
