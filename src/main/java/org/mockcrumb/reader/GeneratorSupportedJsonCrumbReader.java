package org.mockcrumb.reader;

import com.google.gson.Gson;
import org.mockcrumb.exception.MockcrumbException;
import org.mockcrumb.generator.Generator;
import org.mockcrumb.processor.CrumbContentProcessor;
import org.mockcrumb.utils.FileContents;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class GeneratorSupportedJsonCrumbReader extends FileBasedCrumbReader {
    private static final Gson GSON = new Gson();

    private final CrumbContentProcessor processor;

    public GeneratorSupportedJsonCrumbReader(final List<? extends Generator> generators) {
        this.processor = new CrumbContentProcessor(generators);
    }

    @Override
    public <T> T readContext(final Class<T> clazz, final Path context) {
        try {
            return GSON.fromJson(processor.process(FileContents.get(context)), clazz);
        } catch (IOException ex) {
            throw new MockcrumbException(ex);
        }
    }
}
