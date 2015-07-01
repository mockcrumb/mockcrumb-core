package org.mockcrumb.resolver;

import java.nio.file.Path;
import java.nio.file.Paths;

public enum FullyQualifiedCrumbResolver implements CrumbResolver {
    INSTANCE;

    public <T> Path getRelativePath(final Class<T> clazz, final String name) {
        return Paths.get(clazz.getName() + "_" + name);
    }

    public <T> boolean isApplicable(final Class<T> clazz, final Path relativePath) {
        return relativePath.toString().startsWith(clazz.getName() + "_");
    }
}
