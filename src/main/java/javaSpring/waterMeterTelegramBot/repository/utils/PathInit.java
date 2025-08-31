package javaSpring.waterMeterTelegramBot.repository.utils;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Path;

@Component
public class PathInit {

    @Value("${pathInit.nameDataMain}")
    private String nameDataMain;

    @Value("${pathInit.nameUsersCatalog}")
    private String nameUsersCatalog;

    @Value("${pathInit.nameProfilesCatalog}")
    private String nameProfilesCatalog;

    public Path getPathUsers(){
        return Path.of(nameDataMain+ File.separator +nameUsersCatalog);
    }

    public Path getPathProfiles(){
        return Path.of(nameDataMain+ File.separator +nameProfilesCatalog);
    }
}
