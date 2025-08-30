package javaSpring.waterMeterTelegramBot.repository.user;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import javaSpring.waterMeterTelegramBot.data.user.User;
import javaSpring.waterMeterTelegramBot.repository.exeption.FileException;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

@Repository
public class LoadFromFileUsers implements LoaderUsers {
    private Path pathCatalog;

    public LoadFromFileUsers(Path pathCatalog) {
        this.pathCatalog = Path.of(pathCatalog + File.separator + "users");
    }

    @Override
    public void load(Map<String, User> users) {
        if (!Files.exists(pathCatalog)) {
            return;
        }
        try (DirectoryStream<Path> files = Files.newDirectoryStream(pathCatalog)) {
            for (Path path : files) {
                if (path.toFile().isFile()) {
                    User profile = loadOneProfile(path);
                    users.put(profile.name(), profile);
                }
            }
        } catch (IOException e) {
            throw new FileException(e, "Исключние при чтении списка Профилей из файла, класс: " + this.getClass().getSimpleName());
        }
    }

    public User loadOneProfile(Path pathFile) {
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

    @Override
    public User loadUser(String nameUser) {
        Path pathFile = createPathToUser(nameUser);
        return loadOneProfile(pathFile);

    }

    public Path createPathToUser(String nameUser){
        String sep = File.separator;
        return Path.of( pathCatalog + sep + "user" + sep + nameUser +".json");
    }
}
