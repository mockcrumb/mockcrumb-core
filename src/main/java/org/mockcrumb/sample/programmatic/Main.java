package org.mockcrumb.sample.programmatic;

import org.mockcrumb.FileBasedMockcrumbLoader;
import org.mockcrumb.reader.JsonCrumbReader;
import org.mockcrumb.resolver.ClassnameDirectoryCrumbResolver;
import org.mockcrumb.resolver.FullyQualifiedCrumbResolver;
import org.mockcrumb.sample.model.Foo;

import java.io.File;
import java.util.Collection;

public final class Main {
    private Main() {
    }

    public static void main(final String[] args) {
        System.out.println("=== Started");

        FileBasedMockcrumbLoader mockcrumbLoader1 =
                FileBasedMockcrumbLoader.of(
                        new File(Main.class.getClassLoader().getResource("sample-structure1").getPath()).toPath(),
                        FullyQualifiedCrumbResolver.INSTANCE, JsonCrumbReader.INSTANCE);
        run(mockcrumbLoader1, "aaa");

        System.out.println("===");

        FileBasedMockcrumbLoader mockcrumbLoader2 =
                FileBasedMockcrumbLoader.of(
                        new File(Main.class.getClassLoader().getResource("sample-structure2").getPath()).toPath(),
                        ClassnameDirectoryCrumbResolver.INSTANCE, JsonCrumbReader.INSTANCE);
        run(mockcrumbLoader2, "ccc");

        System.out.println("=== Finished");
    }

    public static void run(final FileBasedMockcrumbLoader mockcrumbLoader, final String sampleObjectName) {
        Foo foo1 =  mockcrumbLoader.instance(Foo.class, sampleObjectName);
        System.out.println("Foo: " + foo1.getValue());

        Collection<Foo> fooCollection1 = mockcrumbLoader.instances(Foo.class);
        System.out.println("Foo collection: size: " + fooCollection1.size());
        for (Foo foo: fooCollection1) {
            System.out.println("Foo: " + foo.getValue());
        }
    }
}
