package org.mockcrumb;

import org.mockcrumb.reader.CrumbReader;
import org.mockcrumb.resolver.CrumbResolver;

import java.nio.file.Path;

public final class MockcrumbLoaders {
    private MockcrumbLoaders() {
    }

    public static FileBasedMockcrumbLoader of(final Path contextPath, final CrumbResolver crumbResolver,
                                              final CrumbReader crumbReader) {
        return FileBasedMockcrumbLoader.of(contextPath, crumbResolver, crumbReader);
    }
}
