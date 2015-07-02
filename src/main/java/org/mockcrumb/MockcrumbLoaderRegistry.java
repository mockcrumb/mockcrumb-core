package org.mockcrumb;

import org.mockcrumb.exception.MockcrumbException;

import java.util.HashMap;
import java.util.Map;

public final class MockcrumbLoaderRegistry {
    private Map<String, MockcrumbLoader> loaders = new HashMap<>();

    private MockcrumbLoaderRegistry() {
    }

    public static MockcrumbLoaderRegistry of(final String group, final MockcrumbLoader loader) {
        MockcrumbLoaderRegistry registry = new MockcrumbLoaderRegistry();
        registry.add(group, loader);
        return registry;
    }

    public MockcrumbLoaderRegistry add(final String group, final MockcrumbLoader loader) {
        if (group == null) {
            throw new MockcrumbException("Registered loader group name cannot be null");
        }

        if (loader == null) {
            throw new MockcrumbException("Registered loader cannot be null");
        }
        loaders.put(group, loader);
        return this;
    }

    public MockcrumbLoader get(final String group) {
        if (group != null && loaders.containsKey(group) && loaders.get(group) != null) {
            return loaders.get(group);
        }
        throw new MockcrumbException("Mockcrumb loader not defined for group: " + group);
    }
}
