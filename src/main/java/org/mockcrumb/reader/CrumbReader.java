package org.mockcrumb.reader;

public interface CrumbReader<U> {
    <T> T readContext(final Class<T> clazz, final U context);
}
