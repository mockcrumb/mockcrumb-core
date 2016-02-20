package org.mockcrumb.resolver;

import java.nio.file.Path;

public interface CrumbResolver {
    <T> Path getRelativePath(final Class<T> clazz, final String name);
}
