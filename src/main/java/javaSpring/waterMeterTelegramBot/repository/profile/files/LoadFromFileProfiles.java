package javaSpring.waterMeterTelegramBot.repository.profile.files;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javaSpring.waterMeterTelegramBot.data.profile.Profile;
import javaSpring.waterMeterTelegramBot.repository.exeption.FileException;
import javaSpring.waterMeterTelegramBot.repository.utils.PathConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

@Repository
@ConditionalOnProperty(value="profile.file.select.enable" ,havingValue="true",matchIfMissing = false)
public class LoadFromFileProfiles implements LoaderProfiles {
    private final Path pathCatalog;

    public LoadFromFileProfiles(PathConfiguration pathInit) {
        this.pathCatalog = pathInit.getPathProfiles();
    }

    @Override
    public void load(Map<String, Profile> profiles) {
        if (!Files.exists(pathCatalog)) {
            return;
        }
        try (DirectoryStream<Path> files = Files.newDirectoryStream(pathCatalog)) {
            for (Path path : files) {
                if (path.toFile().isFile()) {
                    Profile profile = loadProfile(path);
                    profiles.put(profile.name(), profile);
                }
            }
        } catch (IOException e) {
            throw new FileException(e, "Исключние при чтении списка Профилей из файла, класс: " + this.getClass().getSimpleName());
        }
    }

    public Profile loadProfile(Path pathFile) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
        Profile profile;
        try {
            profile = mapper.readValue(pathFile.toFile(), Profile.class);
        } catch (IOException e) {
            throw new FileException(e, "Исключние при чтении Профиля из файла, класс: " + this.getClass().getSimpleName());
        }
        return profile;
    }
}
