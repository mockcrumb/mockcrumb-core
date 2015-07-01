package org.mockcrumb.sample.annotation;

import org.mockcrumb.FileBasedMockcrumbLoader;
import org.mockcrumb.annotation.MockcrumbAnnotations;
import org.mockcrumb.annotation.Mockcrumb;
import org.mockcrumb.reader.JsonCrumbReader;
import org.mockcrumb.resolver.FullyQualifiedCrumbResolver;
import org.mockcrumb.sample.model.Foo;

import java.io.File;
import java.nio.file.Path;

public final class Main {
    private Main() {
    }

    public static final class FooEmbedded {
        @Mockcrumb(name = "aaa")
        private Foo foo;
        @Mockcrumb
        private Foo bbb;

        public Foo getFoo() {
            return foo;
        }

        public Foo getBbb() {
            return bbb;
        }
    }

    public static void main(final String[] args) {
        System.out.println("=== Started");

        Path contextPath = new File(Main.class.getClassLoader().getResource("sample-structure1").getPath()).toPath();
        FooEmbedded fooEmbedded = new FooEmbedded();
        MockcrumbAnnotations.init(fooEmbedded, FileBasedMockcrumbLoader.of(contextPath,
                FullyQualifiedCrumbResolver.INSTANCE, JsonCrumbReader.INSTANCE));
        System.out.println(fooEmbedded.getFoo().getValue());
        System.out.println(fooEmbedded.getBbb().getValue());

        System.out.println("=== Finished");
    }
}
