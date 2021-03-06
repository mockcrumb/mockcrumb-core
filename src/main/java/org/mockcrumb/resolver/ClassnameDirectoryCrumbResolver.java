package org.mockcrumb.resolver;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ClassnameDirectoryCrumbResolver extends FileBasedCrumbResolver {
    public static final ClassnameDirectoryCrumbResolver INSTANCE = new ClassnameDirectoryCrumbResolver();

    @Override
    public <T> Path getContext(final Class<T> clazz, final String name) {
        return Paths.get(classNameToPathString(clazz), name);
    }

    <T> String classNameToPathString(final Class<T> clazz) {
        return clazz.getName().replace('.', File.separatorChar);
    }
}
