package org.mockcrumb.sample.programmatic;

import org.mockcrumb.FileBasedMockcrumbLoader;
import org.mockcrumb.reader.JsonCrumbReader;
import org.mockcrumb.resolver.ClassnameDirectoryCrumbResolver;
import org.mockcrumb.resolver.FullyQualifiedCrumbResolver;
import org.mockcrumb.sample.model.Foo;
import org.mockcrumb.sample.utils.SampleUtils;

public final class Main {
    private Main() {
    }

    public static void main(final String[] args) {
        SampleUtils.printStarted();

        FileBasedMockcrumbLoader mockcrumbLoader1 = FileBasedMockcrumbLoader.of(SampleUtils.sampleStructure1Path(),
                FullyQualifiedCrumbResolver.INSTANCE, JsonCrumbReader.INSTANCE);
        run(mockcrumbLoader1, "aaa");

        SampleUtils.printSeparator();

        FileBasedMockcrumbLoader mockcrumbLoader2 = FileBasedMockcrumbLoader.of(SampleUtils.sampleStructure2Path(),
                ClassnameDirectoryCrumbResolver.INSTANCE, JsonCrumbReader.INSTANCE);
        run(mockcrumbLoader2, "ccc");

        SampleUtils.printFinished();
    }

    public static void run(final FileBasedMockcrumbLoader mockcrumbLoader, final String sampleObjectName) {
        Foo foo1 =  mockcrumbLoader.instance(Foo.class, sampleObjectName);
        System.out.println("Foo: " + foo1.getValue());
    }
}
