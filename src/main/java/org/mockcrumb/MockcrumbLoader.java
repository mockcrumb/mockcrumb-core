package org.mockcrumb;

import org.mockcrumb.reader.CrumbReader;
import org.mockcrumb.reader.JsonCrumbReader;
import org.mockcrumb.resolver.CrumbResolver;
import org.mockcrumb.resolver.FullyQualifiedCrumbResolver;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collection;

public final class MockcrumbLoader {
    private Path contextPath;
    private CrumbResolver crumbResolver;
    private CrumbReader crumbReader;

    private MockcrumbLoader() {
    }

    public static MockcrumbLoader of(final Path contextPath, final CrumbResolver crumbResolver) {
        MockcrumbLoader mockcrumbLoader = new MockcrumbLoader();
        mockcrumbLoader.contextPath = contextPath;
        mockcrumbLoader.crumbResolver = crumbResolver;
        mockcrumbLoader.crumbReader = new JsonCrumbReader();
        return mockcrumbLoader;
    }

    public static MockcrumbLoader of(final Path contextPath) {
        return MockcrumbLoader.of(contextPath, new FullyQualifiedCrumbResolver());
    }

    public <T> T instance(final Class<T> clazz, final String name) {
        try {
            return crumbReader.read(clazz,
                    Paths.get(contextPath.toString(), crumbResolver.getRelativePath(clazz, name).toString()));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
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
            e.printStackTrace();
        }

        return instances;
    }
}
