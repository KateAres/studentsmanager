package ua.com.foxminded.studentsmanager.domain;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileDataReader {

    public String getDataFromFile(String filePath) {
        URL fileURL = null;
        try {
            fileURL = getClass().getClassLoader().getResource(filePath);
            return collectData(filePath, fileURL);
        } catch (IOException ex) {
            throw new IllegalArgumentException("Can't read the file: " + filePath, ex);
        } catch (URISyntaxException ex) {
            throw new IllegalArgumentException("Can't covert URL:" + fileURL + "to valid URI", ex);
        }
    }

    private String collectData(String filePath, URL fileURL) throws URISyntaxException, IOException {
        if (fileURL == null) {
            throw new IllegalArgumentException("URI of the file " + filePath + " is empty");
        }
        URI fileUri = fileURL.toURI();
        File file = Paths.get(fileUri).toFile();
        try (Stream<String> sortedDataStream = Files.lines(Paths.get(file.getAbsolutePath()))) {
            return sortedDataStream.collect(Collectors.joining());
        }
    }
}
