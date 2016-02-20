package org.mockcrumb.resolver;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FullyQualifiedCrumbResolver implements CrumbResolver {
    public static final FullyQualifiedCrumbResolver INSTANCE = new FullyQualifiedCrumbResolver();

    @Override
    public <T> Path getRelativePath(final Class<T> clazz, final String name) {
        return Paths.get(clazz.getName() + "_" + name);
    }
}
