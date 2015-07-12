package org.mockcrumb.resolver;

import org.junit.Test;

import java.io.File;
import java.nio.file.Path;

import static org.fest.assertions.Assertions.assertThat;

public class ClassnameDirectoryCrumbResolverTest {
    @Test
    public void shouldGetRelativePath() {
        // Given && When
        String s = File.separator;
        Path path1 = ClassnameDirectoryCrumbResolver.INSTANCE.getRelativePath(org.mockcrumb.test.Foo.class, "sample");

        // Then
        assertThat(path1).isNotNull();
        assertThat(path1.toString()).isEqualTo("org" + s + "mockcrumb" + s + "test" + s + "Foo" + s + "sample");
    }
}
