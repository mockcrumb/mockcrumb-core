package org.mockcrumb;

import org.mockcrumb.reader.FileBasedCrumbReader;
import org.mockcrumb.resolver.FileBasedCrumbResolver;

import java.nio.file.Path;
import java.nio.file.Paths;

public final class FileBasedMockcrumbLoader implements MockcrumbLoader {
    private Path contextPath;
    private FileBasedCrumbResolver crumbResolver;
    private FileBasedCrumbReader crumbReader;

    private FileBasedMockcrumbLoader() {
    }

    public static FileBasedMockcrumbLoader of(final Path contextPath, final FileBasedCrumbResolver crumbResolver,
                                              final FileBasedCrumbReader crumbReader) {
        FileBasedMockcrumbLoader mockcrumbLoader = new FileBasedMockcrumbLoader();
        mockcrumbLoader.contextPath = contextPath;
        mockcrumbLoader.crumbResolver = crumbResolver;
        mockcrumbLoader.crumbReader = crumbReader;
        return mockcrumbLoader;
    }

    public <T> T instance(final Class<T> clazz, final String name) {
        return crumbReader.readPath(
                clazz,
                Paths.get(contextPath.toString(), crumbResolver.getPath(clazz, name).toString()));
    }
}
