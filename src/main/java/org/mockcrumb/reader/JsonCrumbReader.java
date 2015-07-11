package org.mockcrumb.reader;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class JsonCrumbReader implements CrumbReader {
    public static final JsonCrumbReader INSTANCE = new JsonCrumbReader();
    private static final Gson GSON = new Gson();

    public <T> T read(final Class<T> clazz, final Path path) throws IOException {
        StringBuilder builder = new StringBuilder();
        for (String line : Files.readAllLines(path)) {
            builder.append(line);
        }
        return GSON.fromJson(builder.toString(), clazz);
    }
}
