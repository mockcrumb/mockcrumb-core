package org.mockcrumb;

import org.junit.Test;
import org.mockcrumb.resolver.ClassnameDirectoryCrumbResolver;

import java.io.File;
import java.nio.file.Path;

import static org.fest.assertions.Assertions.assertThat;


public class ClassnameDirectoryCrumbResolverTest {
    private ClassnameDirectoryCrumbResolver resolver = new ClassnameDirectoryCrumbResolver();

    @Test
    public void shouldGetRelativePath() {
        // Given && When
        String s = File.separator;
        Path path1 = resolver.getRelativePath(org.mockcrumb.test.Foo.class, "sample");

        // Then
        assertThat(path1).isNotNull();
        assertThat(path1.toString()).isEqualTo("org" + s + "mockcrumb" + s + "test" + s + "Foo" + s + "sample");
    }
}
