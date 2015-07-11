package org.mockcrumb.resolver;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ClassnameDirectoryCrumbResolver implements CrumbResolver {
    public static final ClassnameDirectoryCrumbResolver INSTANCE = new ClassnameDirectoryCrumbResolver();

    public <T> Path getRelativePath(final Class<T> clazz, final String name) {
        return Paths.get(classNameToPathString(clazz), name);
    }

    public <T> boolean isApplicable(final Class<T> clazz, final Path relativePath) {
        return relativePath.toString().startsWith(classNameToPathString(clazz));
    }

    <T> String classNameToPathString(final Class<T> clazz) {
        return clazz.getName().replace('.', File.separatorChar);
    }
}
