package com.github.lucean.advent2020.common.io;

import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.Comparator;
import java.util.List;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;

@Log4j2
@UtilityClass
public class FileUtils {

    public static List<String> fileAsList(final String fileName) throws IOException, URISyntaxException {
        var classLoader = FileUtils.class.getClassLoader();
        var resource = classLoader.getResource(fileName);

        if (resource == null) {
            throw new IllegalArgumentException("File not found: " + fileName);
        } else {
            var filePath = new File(resource.toURI()).toPath();
            try {
                return Files.readAllLines(filePath);
            } catch (final IOException ioe) {
                log.error("Error processing file: {}", ioe.getMessage());
                throw ioe;
            }
        }
    }

    public static <T> List<T> fileAsList(final String fileName, final Class<T> typeClass) throws IOException, URISyntaxException {
        return fileAsList(fileName).stream()
                                   .map(str -> FileUtils.toObject(str, typeClass))
                                   .collect(toList());
    }

    public static <T> List<T> fileAsList(final String fileName, final Class<T> typeClass, final Comparator<T> comparator) throws IOException,
                                                                                                                                 URISyntaxException {
        return fileAsList(fileName).stream()
                                   .map(str -> FileUtils.toObject(str, typeClass))
                                   .sorted(comparator)
                                   .collect(toList());
    }

    private static <T> T toObject(final String string, final Class<T> typeClass) {
        if (Boolean.class == typeClass) {
            return typeClass.cast(Boolean.parseBoolean(string));
        }
        if (Byte.class == typeClass) {
            return typeClass.cast(Byte.parseByte(string));
        }
        if (Short.class == typeClass) {
            return typeClass.cast(Short.parseShort(string));
        }
        if (Integer.class == typeClass) {
            return typeClass.cast(Integer.parseInt(string));
        }
        if (Long.class == typeClass) {
            return typeClass.cast(Long.parseLong(string));
        }
        if (Float.class == typeClass) {
            return typeClass.cast(Float.parseFloat(string));
        }
        if (Double.class == typeClass) {
            return typeClass.cast(Double.parseDouble(string));
        }

        throw new UnsupportedOperationException(format("The type class %s is not currently supported.", typeClass));
    }
}
