package org.mockcrumb.reader;

import java.nio.file.Path;

public abstract class FileBasedCrumbReader implements CrumbReader<Path> {
    public <T> T readPath(final Class<T> clazz, final Path path) {
        return readContext(clazz, path);
    }
}
