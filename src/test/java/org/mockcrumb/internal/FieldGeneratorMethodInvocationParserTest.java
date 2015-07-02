package org.mockcrumb.internal;

import org.junit.Test;

import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;

public class FieldGeneratorMethodInvocationParserTest {
    private FieldGeneratorInvocationParser parser = new FieldGeneratorInvocationParser();

    @Test
    public void shouldParse() {
        // Given && When
        Map<String, FieldGeneratorMethodInvocation> invocations1 =
                parser.parse("sample {{  sampleFunction    (       ) }}  sample ");
        Map<String, FieldGeneratorMethodInvocation> invocations2 =
                parser.parse("sample {{sampleFunction() }}{{test()}}   test    {{     another()}}     sample ");

        // Then
        assertThat(invocations1).hasSize(1);
        assertThat(invocations1.get("{{  sampleFunction    (       ) }}")).isNotNull();

        assertThat(invocations2).hasSize(3);
        assertThat(invocations2.get("{{sampleFunction() }}")).isNotNull();
        assertThat(invocations2.get("{{test()}}")).isNotNull();
        assertThat(invocations2.get("{{     another()}}")).isNotNull();
    }
}
