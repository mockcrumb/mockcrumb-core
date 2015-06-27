package org.mockcrumb.sample.programmatic;

import org.mockcrumb.resolver.ClassnameDirectoryCrumbResolver;
import org.mockcrumb.MockcrumbLoader;
import org.mockcrumb.sample.model.Foo;

import java.io.File;
import java.util.Collection;

public final class Main {
    private Main() {
    }

    public static void main(final String[] args) {
        System.out.println("=== Started");

        MockcrumbLoader mockcrumbLoader1 =
                MockcrumbLoader.of(
                        new File(Main.class.getClassLoader().getResource("sample-structure1").getPath()).toPath());
        run(mockcrumbLoader1, "aaa");

        System.out.println("===");

        MockcrumbLoader mockcrumbLoader2 =
                MockcrumbLoader.of(
                        new File(Main.class.getClassLoader().getResource("sample-structure2").getPath()).toPath(),
                        new ClassnameDirectoryCrumbResolver());
        run(mockcrumbLoader2, "ccc");

        System.out.println("=== Finished");
    }

    public static void run(final MockcrumbLoader mockcrumbLoader, final String sampleObjectName) {
        Foo foo1 =  mockcrumbLoader.instance(Foo.class, sampleObjectName);
        System.out.println("Foo: " + foo1.getValue());

        Collection<Foo> fooCollection1 = mockcrumbLoader.instances(Foo.class);
        System.out.println("Foo collection: size: " + fooCollection1.size());
        for (Foo foo: fooCollection1) {
            System.out.println("Foo: " + foo.getValue());
        }
    }
}
