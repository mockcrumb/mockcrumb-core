package org.mockcrumb.processor;

import com.google.gson.Gson;
import org.mockcrumb.exception.MockcrumbException;
import org.mockcrumb.generator.Generator;
import org.mockcrumb.internal.GeneratorInvocationParser;
import org.mockcrumb.internal.GeneratorMethodInvocation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CrumbContentProcessor {
    private static final Gson GSON = new Gson();

    private final List<? extends Generator> generators;

    public CrumbContentProcessor(final List<? extends Generator> generators) {
        if (generators != null) {
            this.generators = new ArrayList<>(generators);
        } else {
            this.generators = new ArrayList<>();
        }
    }

    public String process(final String content) {
        // Read invocations
        Map<String, GeneratorMethodInvocation> invocations = new GeneratorInvocationParser().parse(content);

        // Process content
        String processedContent = content;
        for (String invocationString : invocations.keySet()) {
            GeneratorMethodInvocation invocation = invocations.get(invocationString);
            boolean generatorFound = false;
            for (Generator generator : generators) {
                if (invocation.getName().equals(getName(generator))) {
                    // Replace in content
                    processedContent = processedContent.replace(invocationString, GSON.toJson(generator.generate()));
                    generatorFound = true;
                    break;
                }
            }

            if (!generatorFound) {
                throw new MockcrumbException("Generator not found for invocation: " + invocationString);
            }
        }
        return processedContent;
    }

    String getName(final Generator generator) {
        String simpleName = generator.getClass().getSimpleName();
        return simpleName.substring(0, 1).toLowerCase() + simpleName.substring(1, simpleName.length());
    }
}
