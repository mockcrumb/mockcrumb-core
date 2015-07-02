package org.mockcrumb.internal;

import org.mockcrumb.exception.MockcrumbException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FieldGeneratorMethodInvocation {
    private static final Pattern METHOD_PATTERN = Pattern.compile("([^(]*)\\(([^)]*)\\)");
    private static final Pattern METHOD_NAME_PATTERN = Pattern.compile("[a-zA-Z][a-zA-Z0-9]*");

    private String name;
    private List<Object> args;

    public static FieldGeneratorMethodInvocation of(final String initInvocationString) {
        String invocationString = initInvocationString.trim();
        Matcher methodNameMatcher = METHOD_PATTERN.matcher(invocationString);
        if (methodNameMatcher.matches()) {
            String name = methodNameMatcher.group(1).trim();
            String argsString = methodNameMatcher.group(2).trim();
            if (!METHOD_NAME_PATTERN.matcher(name).matches()) {
                throw new MockcrumbException("Invalid method name: " + invocationString);
            }
            if (!"".equals(argsString)) {
                throw new MockcrumbException("Method with parameters not supported yet: " + invocationString);
            }

            FieldGeneratorMethodInvocation invocation = new FieldGeneratorMethodInvocation();
            invocation.name = name;
            invocation.args = new ArrayList<>();
            return invocation;
        } else {
            throw new MockcrumbException("Invalid invocation: " + invocationString);
        }
    }

    public String getName() {
        return name;
    }

    public List<Object> getArgs() {
        return args;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FieldGeneratorMethodInvocation that = (FieldGeneratorMethodInvocation) o;
        return Objects.equals(name, that.name) && Objects.equals(args, that.args);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, args);
    }
}
