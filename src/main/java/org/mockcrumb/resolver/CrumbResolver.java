package org.mockcrumb.resolver;

public interface CrumbResolver<U> {
    <T> U getContext(final Class<T> clazz, final String name);
}
