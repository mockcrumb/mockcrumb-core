package org.mockcrumb.exception;

public class MockcrumbException extends RuntimeException {
    public MockcrumbException(final Throwable t) {
        super(t);
    }
}
