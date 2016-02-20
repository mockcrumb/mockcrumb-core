package org.mockcrumb.resolver;

import java.nio.file.Path;

public abstract class FileBasedCrumbResolver implements CrumbResolver<Path> {
    public <T> Path getPath(final Class<T> clazz, final String name) {
        return getContext(clazz, name);
    }
}
