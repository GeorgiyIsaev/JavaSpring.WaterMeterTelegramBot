package javaSpring.waterMeterTelegramBot.repository.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Path;

//@ConfigurationProperties(prefix = "pathConfiguration")
@Component
public class PathConfiguration {
    @Value("${pathConfiguration.nameDataMain}")
    private String nameDataMain;

    @Value("${pathConfiguration.nameUsersCatalog}")
    private String nameUsersCatalog;

    @Value("${pathConfiguration.nameProfilesCatalog}")
    private String nameProfilesCatalog;

    public Path getPathUsers() {
        return Path.of(nameDataMain + File.separator + nameUsersCatalog);
    }

    public Path getPathProfiles() {
        return Path.of(nameDataMain + File.separator + nameProfilesCatalog);
    }

}
