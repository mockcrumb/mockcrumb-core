package org.mockcrumb.reader;

import com.google.gson.Gson;
import org.mockcrumb.exception.MockcrumbException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class JsonCrumbReader extends FileBasedCrumbReader {
    public static final JsonCrumbReader INSTANCE = new JsonCrumbReader();
    private static final Gson GSON = new Gson();

    @Override
    public <T> T readContext(final Class<T> clazz, final Path context) {
        try {
            StringBuilder builder = new StringBuilder();
            for (String line : Files.readAllLines(context)) {
                builder.append(line);
            }
            return GSON.fromJson(builder.toString(), clazz);
        } catch (IOException ex) {
            throw new MockcrumbException(ex);
        }
    }
}
