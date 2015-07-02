package org.mockcrumb.processor;

import org.junit.Test;
import org.mockcrumb.exception.MockcrumbException;
import org.mockcrumb.generator.Generator;
import org.mockcrumb.processor.CrumbContentProcessor;

import java.util.Arrays;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.fail;

public class CrumbContentProcessorTest {
    @Test
    public void shouldProcess() {
        // Given && When
        String processedContent1 =
                new CrumbContentProcessor(null).process("sample");
        String processedContent2 =
                new CrumbContentProcessor(Arrays.asList(new FooGenerator())).process("sample {{fooGenerator()}}");
        String processedContent3 =
                new CrumbContentProcessor(Arrays.asList(new OneGenerator())).process("sample {{oneGenerator()}}");

        // Then
        assertThat(processedContent1).isEqualTo("sample");
        assertThat(processedContent2).isEqualTo("sample \"Foo\"");
        assertThat(processedContent3).isEqualTo("sample 1");
    }

    @Test
    public void shouldNotProcess() {
        try {
            // Given && When && Then
            new CrumbContentProcessor(Arrays.asList(new OneGenerator())).process("sample {{fooGenerator()}}");
            fail();
        } catch (MockcrumbException e) {
            // Expected
        }
    }

    @Test
    public void shouldGetName() {
        // Given && When
        String generatorName = new CrumbContentProcessor(null).getName(new FooGenerator());

        // Then
        assertThat(generatorName).isEqualTo("fooGenerator");
    }

    static class FooGenerator implements Generator {
        @Override
        public String generate() {
            return "Foo";
        }
    }

    static class OneGenerator implements Generator {
        @Override
        public Integer generate() {
            return 1;
        }
    }
}
