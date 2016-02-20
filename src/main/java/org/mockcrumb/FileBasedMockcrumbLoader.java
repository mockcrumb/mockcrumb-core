package org.mockcrumb;

import org.mockcrumb.exception.MockcrumbException;
import org.mockcrumb.reader.CrumbReader;
import org.mockcrumb.resolver.CrumbResolver;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class FileBasedMockcrumbLoader implements MockcrumbLoader {
    private Path contextPath;
    private CrumbResolver crumbResolver;
    private CrumbReader crumbReader;

    private FileBasedMockcrumbLoader() {
    }

    public static FileBasedMockcrumbLoader of(final Path contextPath, final CrumbResolver crumbResolver,
                                              final CrumbReader crumbReader) {
        FileBasedMockcrumbLoader mockcrumbLoader = new FileBasedMockcrumbLoader();
        mockcrumbLoader.contextPath = contextPath;
        mockcrumbLoader.crumbResolver = crumbResolver;
        mockcrumbLoader.crumbReader = crumbReader;
        return mockcrumbLoader;
    }

    public <T> T instance(final Class<T> clazz, final String name) {
        try {
            return crumbReader.read(clazz,
                    Paths.get(contextPath.toString(), crumbResolver.getRelativePath(clazz, name).toString()));
        } catch (IOException e) {
            throw new MockcrumbException(e);
        }
    }
}
