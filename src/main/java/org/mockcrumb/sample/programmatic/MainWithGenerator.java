package org.mockcrumb.sample.programmatic;

import org.mockcrumb.FileBasedMockcrumbLoader;
import org.mockcrumb.generator.Generator;
import org.mockcrumb.reader.GeneratorSupportedJsonCrumbReader;
import org.mockcrumb.resolver.ClassnameDirectoryCrumbResolver;
import org.mockcrumb.sample.model.Foo;
import org.mockcrumb.sample.utils.SampleUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

class LocalDateTimeGenerator implements Generator {
    @Override
    public String generate() {
        return LocalDateTime.now(ZoneId.of("UTC")).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }
}

public final class MainWithGenerator {
    private MainWithGenerator() {
    }

    public static void main(final String[] args) {
        SampleUtils.printStarted();

        GeneratorSupportedJsonCrumbReader reader =
                new GeneratorSupportedJsonCrumbReader(Arrays.asList(new LocalDateTimeGenerator()));

        FileBasedMockcrumbLoader mockcrumbLoader = FileBasedMockcrumbLoader.of(
                SampleUtils.sampleStructure3Path(), ClassnameDirectoryCrumbResolver.INSTANCE, reader);

        Foo foo1 =  mockcrumbLoader.instance(Foo.class, "ccc_withGenerator");
        System.out.println("Foo: " + foo1.getValue());

        SampleUtils.printSeparator();

        Foo foo2 =  mockcrumbLoader.instance(Foo.class, "ccc_withGenerator");
        System.out.println("Foo: " + foo2.getValue());

        SampleUtils.printFinished();
    }
}
