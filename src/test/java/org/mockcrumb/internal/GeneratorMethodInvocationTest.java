package org.mockcrumb.internal;

import org.junit.Test;
import org.mockcrumb.exception.MockcrumbException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

public class GeneratorMethodInvocationTest {
    @Test
    public void shouldCreateInstance() {
        // Given && When
        GeneratorMethodInvocation invocation1 = GeneratorMethodInvocation.of("test()");
        GeneratorMethodInvocation invocation2 = GeneratorMethodInvocation.of("   tESt()   ");
        GeneratorMethodInvocation invocation3 = GeneratorMethodInvocation.of("   test1     (         )   ");

        // Then
        assertThat(invocation1).isNotNull();
        assertThat(invocation1.getName()).isEqualTo("test");
        assertThat(invocation1.getArgs()).hasSize(0);

        assertThat(invocation2).isNotNull();
        assertThat(invocation2.getName()).isEqualTo("tESt");
        assertThat(invocation2.getArgs()).hasSize(0);

        assertThat(invocation3).isNotNull();
        assertThat(invocation3.getName()).isEqualTo("test1");
        assertThat(invocation3.getArgs()).hasSize(0);
    }

    @Test
    public void shouldNotCreateInstance() {
        try {
            // Given && When && Then
            GeneratorMethodInvocation.of("te st()");
            fail();
        } catch (MockcrumbException e) {
            // Expected
        }

        try {
            // Given && When && Then
            GeneratorMethodInvocation.of("test(1)");
            fail();
        } catch (MockcrumbException e) {
            // Expected
        }

        try {
            // Given && When && Then
            GeneratorMethodInvocation.of("test(");
            fail();
        } catch (MockcrumbException e) {
            // Expected
        }

        try {
            // Given && When && Then
            GeneratorMethodInvocation.of("test)");
            fail();
        } catch (MockcrumbException e) {
            // Expected
        }

        try {
            // Given && When && Then
            GeneratorMethodInvocation.of("1test()");
            fail();
        } catch (MockcrumbException e) {
            // Expected
        }
    }
}
