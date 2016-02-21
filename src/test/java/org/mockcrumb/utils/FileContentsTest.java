package org.mockcrumb.utils;

import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

public class FileContentsTest {
    @Test
    public void shouldGetContentsEmpty() throws URISyntaxException, IOException {
        // Given && When
        Path path1 = Paths.get(
                FileContentsTest.class.getClassLoader()
                        .getResource("sample-structure1/org.mockcrumb.sample.model.Foo_blank").toURI());
        // When
        String contents1 = FileContents.get(path1);

        // Then
        assertThat(contents1).isEqualTo("");
    }

    @Test
    public void shouldGetContents() throws URISyntaxException, IOException {
        // Given && When
        Path path1 = Paths.get(
                FileContentsTest.class.getClassLoader()
                        .getResource("sample-structure1/org.mockcrumb.sample.model.Foo_aaa").toURI());
        // When
        String contents1 = FileContents.get(path1);

        // Then
        assertThat(contents1).isEqualTo(
                "{\n" +
                "    \"value\": \"Value of aaa\"\n" +
                "}");
    }
}
