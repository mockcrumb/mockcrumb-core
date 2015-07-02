package org.mockcrumb.exception;

public class MockcrumbException extends RuntimeException {
    public MockcrumbException(final String message) {
        super(message);
    }

    public MockcrumbException(final Throwable t) {
        super(t);
    }
}
