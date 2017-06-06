package br.com.quantati.clienteweb.infra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.util.FileSystemUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * Created by carlos on 25/05/2017.
 */
@Component
@PropertySource("classpath:global.properties")
public class FileSaver {

    private final Path rootLocation;

    @Autowired
    public FileSaver(@Value("${upload.directory}") String baseFolder) {
        this.rootLocation = Paths.get(baseFolder);
        if (!Files.exists(rootLocation)) {
            try {
                Files.createDirectory(rootLocation);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void store(String fileName, byte[] bytes) {
        try {
            Files.copy(new ByteArrayInputStream(bytes), this.rootLocation.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(String.format("Failed to store file %s.", fileName), e);
        }
    }

    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new RuntimeException(String.format("Could not read file %s ", filename));

            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(String.format("Could not read file %s ", filename), e);
        }
    }

    public void delete(String fileName) {
        FileSystemUtils.deleteRecursively(load(fileName).toFile());
    }

}
