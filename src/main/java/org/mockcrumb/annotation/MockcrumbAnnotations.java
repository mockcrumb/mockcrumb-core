package org.mockcrumb.annotation;

import org.mockcrumb.MockcrumbLoader;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public final class MockcrumbAnnotations {
    private MockcrumbAnnotations() {
    }

    public static <T> void init(final T object, final MockcrumbLoader mockcrumbLoader) {
        for (Field field : object.getClass().getDeclaredFields()) {
            for (Annotation annotation : field.getDeclaredAnnotations()) {
                if (annotation instanceof Mockcrumb) {
                    Mockcrumb mockcrumb = (Mockcrumb) annotation;
                    try {
                        // TODO(maciejgowin) Use field name if name not specified
                        field.setAccessible(true);
                        field.set(object, mockcrumbLoader.instance(field.getType(), mockcrumb.name()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
