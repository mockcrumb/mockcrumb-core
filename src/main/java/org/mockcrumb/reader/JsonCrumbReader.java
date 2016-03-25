package org.mockcrumb.reader;

import com.google.gson.Gson;
import org.mockcrumb.exception.MockcrumbException;
import org.mockcrumb.utils.FileContents;

import java.io.IOException;
import java.nio.file.Path;

public class JsonCrumbReader extends FileBasedCrumbReader {
    public static final JsonCrumbReader INSTANCE = new JsonCrumbReader();
    private static final Gson GSON = new Gson();

    @Override
    public <T> T readContext(final Class<T> clazz, final Path context) {
        try {
            return GSON.fromJson(FileContents.get(context), clazz);
        } catch (IOException ex) {
            throw new MockcrumbException(ex);
        }
    }
}
