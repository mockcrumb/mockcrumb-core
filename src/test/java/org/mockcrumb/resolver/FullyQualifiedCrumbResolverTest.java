package org.mockcrumb.resolver;

import org.junit.Test;

import java.io.File;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

public class FullyQualifiedCrumbResolverTest {
    @Test
    public void shouldGetRelativePath() {
        // Given && When
        String s = File.separator;
        Path path1 = FullyQualifiedCrumbResolver.INSTANCE.getPath(org.mockcrumb.test.Foo.class, "sample");

        // Then
        assertThat(path1).isNotNull();
        assertThat(path1.toString()).isEqualTo("org.mockcrumb.test.Foo_sample");
    }
}
