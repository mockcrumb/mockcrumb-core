package org.mockcrumb.internal;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GeneratorInvocationParser {
    private static final Pattern INVOCATION_PATTERN = Pattern.compile("\\{\\{([^}]*)\\}\\}");

    public Map<String, GeneratorMethodInvocation> parse(final String content) {
        Map<String, GeneratorMethodInvocation> invocations = new HashMap<>();
        Matcher matcher = INVOCATION_PATTERN.matcher(content);
        while (matcher.find()) {
            if (matcher.groupCount() > 0) {
                String invocationString = matcher.group(1);
                invocations.put(
                        "{{" + invocationString + "}}",
                        GeneratorMethodInvocation.of(invocationString));
            }
        }
        return invocations;
    }
}

