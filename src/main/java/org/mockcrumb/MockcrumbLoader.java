package org.mockcrumb;

public interface MockcrumbLoader {
    <T> T instance(final Class<T> clazz, final String name);
}
