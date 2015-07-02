package org.mockcrumb.reader;

import com.google.gson.Gson;
import org.mockcrumb.generator.Generator;
import org.mockcrumb.processor.CrumbContentProcessor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class GeneratorSupportedJsonCrumbReader implements CrumbReader {
    private static final Gson GSON = new Gson();

    private final CrumbContentProcessor processor;

    public GeneratorSupportedJsonCrumbReader(final List<Generator> generators) {
        this.processor = new CrumbContentProcessor(generators);
    }

    public <T> T read(final Class<T> clazz, final Path path) throws IOException {
        StringBuilder builder = new StringBuilder();
        for (String line : Files.readAllLines(path)) {
            builder.append(line);
        }
        return GSON.fromJson(processor.process(builder.toString()), clazz);
    }
}
