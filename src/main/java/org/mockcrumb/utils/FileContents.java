package org.mockcrumb.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class FileContents {
    private FileContents() {
    }

    public static String get(final Path path) throws IOException {
        StringBuilder builder = new StringBuilder();
        for (String line : Files.readAllLines(path)) {
            if (builder.length() > 0) {
                builder.append("\n");
            }
            builder.append(line);
        }
        return builder.toString();
    }
}
