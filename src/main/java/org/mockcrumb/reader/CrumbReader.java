package org.mockcrumb.reader;

import java.io.IOException;
import java.nio.file.Path;

public interface CrumbReader {
    <T> T read(final Class<T> clazz, final Path path) throws IOException;
}
