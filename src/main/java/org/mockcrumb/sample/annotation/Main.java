package org.mockcrumb.sample.annotation;

import org.mockcrumb.FileBasedMockcrumbLoader;
import org.mockcrumb.MockcrumbLoaderRegistry;
import org.mockcrumb.annotation.MockcrumbAnnotations;
import org.mockcrumb.annotation.Mockcrumb;
import org.mockcrumb.reader.JsonCrumbReader;
import org.mockcrumb.resolver.FullyQualifiedCrumbResolver;
import org.mockcrumb.sample.model.Foo;
import org.mockcrumb.sample.utils.SampleUtils;

final class FooEmbedded1 {
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

final class FooEmbedded2 {
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

public final class Main {
    private Main() {
    }

    public static void main(final String[] args) {
        SampleUtils.printStarted();

        FileBasedMockcrumbLoader loader = FileBasedMockcrumbLoader.of(SampleUtils.sampleStructure1Path(),
                FullyQualifiedCrumbResolver.INSTANCE, JsonCrumbReader.INSTANCE);

        FooEmbedded1 fooEmbedded1 = new FooEmbedded1();
        MockcrumbAnnotations.init(fooEmbedded1, loader);
        System.out.println(fooEmbedded1.getFoo().getValue());
        System.out.println(fooEmbedded1.getBbb().getValue());

        SampleUtils.printSeparator();

        FooEmbedded2 fooEmbedded2 = new FooEmbedded2();
        MockcrumbAnnotations.init(fooEmbedded2,
                MockcrumbLoaderRegistry.of(Mockcrumb.DEFAULT_LOADER_GROUP, loader).add("another", loader));
        System.out.println(fooEmbedded2.getFoo().getValue());
        System.out.println(fooEmbedded2.getBbb().getValue());
        System.out.println(fooEmbedded2.getFooAnother().getValue());
        System.out.println(fooEmbedded2.getBbbAnother().getValue());

        SampleUtils.printFinished();
    }
}
