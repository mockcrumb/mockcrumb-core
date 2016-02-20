package org.mockcrumb.resolver;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FullyQualifiedCrumbResolver extends FileBasedCrumbResolver {
    public static final FullyQualifiedCrumbResolver INSTANCE = new FullyQualifiedCrumbResolver();

    @Override
    public <T> Path getContext(final Class<T> clazz, final String name) {
        return Paths.get(clazz.getName() + "_" + name);
    }
}
