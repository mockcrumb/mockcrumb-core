package org.mockcrumb;

import org.mockcrumb.exception.MockcrumbException;
import org.mockcrumb.reader.CrumbReader;
import org.mockcrumb.resolver.CrumbResolver;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collection;

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

    public <T> Collection<T> instances(final Class<T> clazz) {
        final Collection<T> instances = new ArrayList<>();
        try {
            Files.walkFileTree(contextPath, new FileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(final Path dir, final BasicFileAttributes attrs)
                        throws IOException {
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(final Path file, final BasicFileAttributes attrs) throws IOException {
                    Path relativePath = contextPath.relativize(file);
                    if (crumbResolver.isApplicable(clazz, relativePath)) {
                        instances.add(crumbReader.read(clazz, file));
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(final Path file, final IOException exc) throws IOException {
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(final Path dir, final IOException exc) throws IOException {
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            throw new MockcrumbException(e);
        }

        return instances;
    }
}
