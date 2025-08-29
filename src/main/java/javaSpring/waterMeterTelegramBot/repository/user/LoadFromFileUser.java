package javaSpring.waterMeterTelegramBot.repository.user;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import javaSpring.waterMeterTelegramBot.data.profile.Profile;
import javaSpring.waterMeterTelegramBot.data.user.User;
import javaSpring.waterMeterTelegramBot.repository.exeption.FileException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class LoadFromFileUser implements LoaderUser {
    private Path pathCatalog;
    private final Profile profile;

    public Profile getProfile() {
        return profile;
    }

    public LoadFromFileUser(Path pathCatalog, Profile profile) {
        this.pathCatalog = pathCatalog;
        this.profile = profile;
    }

    public Path createPathToUser(String nameUser){
        String sep = File.separator;
        return Path.of( pathCatalog + sep + getProfile().name() + sep + nameUser +".json");
    }


    @Override
    public User load(String nameUser) {
        Path pathFile = createPathToUser(nameUser);
        return load(pathFile);

    }

    public User load(Path pathFile) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
        User user;
        try {
            user = mapper.readValue(pathFile.toFile(), User.class);
        } catch (IOException e) {
            throw new FileException(e, "Исключние при чтении User из файла, класс: " + this.getClass().getSimpleName());
        }
        return user;
    }


}
