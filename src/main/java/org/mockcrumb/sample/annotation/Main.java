package org.mockcrumb.sample.annotation;

import org.mockcrumb.FileBasedMockcrumbLoader;
import org.mockcrumb.MockcrumbLoaderRegistry;
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

    public static final class FooEmbedded1 {
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

    public static final class FooEmbedded2 {
        @Mockcrumb(name = "aaa")
        private Foo foo;
        @Mockcrumb
        private Foo bbb;
        @Mockcrumb(name = "aaa", loaderGroup = "another")
        private Foo fooAnother;
        @Mockcrumb(name = "bbb", loaderGroup = "another")
        private Foo bbbAnother;

        public Foo getFoo() {
            return foo;
        }

        public Foo getBbb() {
            return bbb;
        }

        public Foo getFooAnother() {
            return fooAnother;
        }

        public Foo getBbbAnother() {
            return bbbAnother;
        }
    }

    public static void main(final String[] args) {
        System.out.println("=== Started");

        Path contextPath = new File(Main.class.getClassLoader().getResource("sample-structure1").getPath()).toPath();
        FileBasedMockcrumbLoader loader = FileBasedMockcrumbLoader.of(contextPath,
                FullyQualifiedCrumbResolver.INSTANCE, JsonCrumbReader.INSTANCE);

        FooEmbedded1 fooEmbedded1 = new FooEmbedded1();
        MockcrumbAnnotations.init(fooEmbedded1, loader);
        System.out.println(fooEmbedded1.getFoo().getValue());
        System.out.println(fooEmbedded1.getBbb().getValue());

        FooEmbedded2 fooEmbedded2 = new FooEmbedded2();
        MockcrumbAnnotations.init(fooEmbedded2,
                MockcrumbLoaderRegistry.of(Mockcrumb.DEFAULT_LOADER_GROUP, loader).add("another", loader));
        System.out.println(fooEmbedded2.getFoo().getValue());
        System.out.println(fooEmbedded2.getBbb().getValue());
        System.out.println(fooEmbedded2.getFooAnother().getValue());
        System.out.println(fooEmbedded2.getBbbAnother().getValue());

        System.out.println("=== Finished");
    }
}
