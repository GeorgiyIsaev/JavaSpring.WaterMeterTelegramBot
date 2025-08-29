package javaSpring.waterMeterTelegramBot.repository.profile;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import javaSpring.waterMeterTelegramBot.data.profile.Profile;
import javaSpring.waterMeterTelegramBot.repository.exeption.FileException;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Repository
public class SaveInFileProfile implements SaverProfile {

    private final Path pathCatalog;

    public SaveInFileProfile(Path pathCatalog) {
        this.pathCatalog = pathCatalog;
    }


    private void createDirectoriesIfNotExists() {
        Path directory = pathCatalog.getParent();
        if(directory != null && !Files.isDirectory(directory)){
            try {
                Files.createDirectories(directory);
            } catch (IOException e) {
                throw new FileException(e, "Исключение при создании папки, класс:" + this.getClass().getSimpleName());
            }
        }
    }

    @Override
    public void save(Profile profile){
        createDirectoriesIfNotExists();

        Path profileFile = Path.of(pathCatalog + File.separator + profile.name() + ".json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT); //разрешить перенос по строкам
        try {
            mapper.writeValue(profileFile.toFile(), profile);
        } catch (IOException e) {
            throw new FileException(e, "Исключние при чтении User из файла, класс: " + this.getClass().getSimpleName());
                    }
    }

}
