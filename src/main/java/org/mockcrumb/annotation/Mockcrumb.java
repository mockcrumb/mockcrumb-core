package org.mockcrumb.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Mockcrumb {
    String name() default DEFAULT_NAME;
    String loaderGroup() default DEFAULT_LOADER_GROUP;

    String DEFAULT_NAME = "@@DEFAULT@@";
    String DEFAULT_LOADER_GROUP = "@@DEFAULT@@";
}
