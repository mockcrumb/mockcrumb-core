package org.mockcrumb;

import org.junit.Test;
import org.mockcrumb.exception.MockcrumbException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

public final class MockcrumbLoaderRegistryTest {
    MockcrumbLoader loader1 = FileBasedMockcrumbLoader.of(null, null, null);
    MockcrumbLoader loader2 = FileBasedMockcrumbLoader.of(null, null, null);

    @Test
    public void shouldCreateAndAddAndGet() {
        // Given && When
        MockcrumbLoaderRegistry registry1 = MockcrumbLoaderRegistry.of("test", loader1);
        MockcrumbLoaderRegistry registry2 = MockcrumbLoaderRegistry.of("test", loader1).add("another", loader2);

        // Then
        assertThat(registry1.get("test")).isNotNull();
        assertThat(registry1.get("test")).isEqualTo(loader1);

        assertThat(registry2.get("test")).isNotNull();
        assertThat(registry2.get("test")).isEqualTo(loader1);
        assertThat(registry2.get("another")).isNotNull();
        assertThat(registry2.get("another")).isEqualTo(loader2);
    }

    @Test
    public void shouldNotCreate() {
        try {
            // Given && When && Then
            MockcrumbLoaderRegistry.of("test", null);
            fail();
        } catch (MockcrumbException e) {
            // Expected
        }

        try {
            // Given && When && Then
            MockcrumbLoaderRegistry.of(null, null);
            fail();
        } catch (MockcrumbException e) {
            // Expected
        }

        try {
            // Given && When && Then
            MockcrumbLoaderRegistry.of(null, loader1);
            fail();
        } catch (MockcrumbException e) {
            // Expected
        }
    }

    @Test
    public void shouldNotAdd() {
        // Given
        MockcrumbLoaderRegistry registry1 = MockcrumbLoaderRegistry.of("test", loader1);

        try {
            // Given && When && Then
            registry1.add("test", null);
            fail();
        } catch (MockcrumbException e) {
            // Expected
        }

        try {
            // Given && When && Then
            registry1.add(null, null);
            fail();
        } catch (MockcrumbException e) {
            // Expected
        }

        try {
            // Given && When && Then
            registry1.add(null, loader1);
            fail();
        } catch (MockcrumbException e) {
            // Expected
        }
    }

    @Test
    public void shouldNotGet() {
        // Given
        MockcrumbLoaderRegistry registry1 = MockcrumbLoaderRegistry.of("test", loader1);

        try {
            // Given && When && Then
            registry1.get(null);
            fail();
        } catch (MockcrumbException e) {
            // Expected
        }

        try {
            // Given && When && Then
            registry1.get("another");
            fail();
        } catch (MockcrumbException e) {
            // Expected
        }
    }
}
