package org.mockcrumb.sample.annotation;

import org.mockcrumb.annotation.MockcrumbAnnotations;
import org.mockcrumb.annotation.Mockcrumb;
import org.mockcrumb.sample.model.Foo;

import java.io.File;
import java.nio.file.Path;

public final class Main {
    private Main() {
    }

    public static final class FooEmbedded {
        @Mockcrumb(name = "aaa")
        private Foo foo;

        public String getFooValue() {
            return foo != null ? foo.getValue() : null;
        }
    }

    public static void main(final String[] args) {
        System.out.println("=== Started");

        Path contextPath = new File(Main.class.getClassLoader().getResource("sample-structure1").getPath()).toPath();
        FooEmbedded fooEmbedded = new FooEmbedded();
        MockcrumbAnnotations.init(fooEmbedded, contextPath);
        System.out.println(fooEmbedded.getFooValue());

        System.out.println("=== Finished");
    }
}
