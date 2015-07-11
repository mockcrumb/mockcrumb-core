package org.mockcrumb.sample.utils;

import java.io.File;
import java.nio.file.Path;

public final class SampleUtils {
    private SampleUtils() {
    }

    public static Path sampleStructure1Path() {
        return new File(SampleUtils.class.getClassLoader().getResource("sample-structure1").getPath()).toPath();
    }

    public static Path sampleStructure2Path() {
        return new File(SampleUtils.class.getClassLoader().getResource("sample-structure2").getPath()).toPath();
    }

    public static Path sampleStructure3Path() {
        return new File(SampleUtils.class.getClassLoader().getResource("sample-structure3").getPath()).toPath();
    }

    public static void printStarted() {
        System.out.println("=== Started");
    }

    public static void printFinished() {
        System.out.println("=== Finished");
    }

    public static void printSeparator() {
        System.out.println("===");
    }
}
