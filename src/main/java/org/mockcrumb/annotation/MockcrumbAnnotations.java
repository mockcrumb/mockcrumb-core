package org.mockcrumb.annotation;

import org.mockcrumb.MockcrumbLoader;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.nio.file.Path;

public final class MockcrumbAnnotations {
    private MockcrumbAnnotations() {
    }

    public static <T> void init(final T object, final Path contextPath) {
        for (Field field : object.getClass().getDeclaredFields()) {
            for (Annotation annotation : field.getDeclaredAnnotations()) {
                if (annotation instanceof Mockcrumb) {
                    Mockcrumb mockcrumb = (Mockcrumb) annotation;
                    try {
                        field.setAccessible(true);
                        field.set(object, MockcrumbLoader.of(contextPath).instance(field.getType(), mockcrumb.name()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
