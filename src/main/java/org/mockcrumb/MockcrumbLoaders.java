package org.mockcrumb;

import org.mockcrumb.reader.FileBasedCrumbReader;
import org.mockcrumb.resolver.FileBasedCrumbResolver;

import java.nio.file.Path;

public final class MockcrumbLoaders {
    private MockcrumbLoaders() {
    }

    public static FileBasedMockcrumbLoader of(final Path contextPath, final FileBasedCrumbResolver crumbResolver,
                                              final FileBasedCrumbReader crumbReader) {
        return FileBasedMockcrumbLoader.of(contextPath, crumbResolver, crumbReader);
    }
}
