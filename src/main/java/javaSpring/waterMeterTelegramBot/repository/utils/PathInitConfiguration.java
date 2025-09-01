package javaSpring.waterMeterTelegramBot.repository.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.File;
import java.nio.file.Path;

@ConfigurationProperties(prefix = "app")
public record PathInitConfiguration(String nameDataMain,
                                    String nameProfilesCatalog,
                                    String nameUsersCatalog) {

    public Path getPathUsers(){
        return Path.of(nameDataMain+ File.separator +nameUsersCatalog);
    }

    public Path getPathProfiles(){
        return Path.of(nameDataMain+ File.separator +nameProfilesCatalog);
    }

}
