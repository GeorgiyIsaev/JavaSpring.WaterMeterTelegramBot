package javaSpring.waterMeterTelegramBot.storingUserData;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import javaSpring.waterMeterTelegramBot.data.profile.Profile;
import javaSpring.waterMeterTelegramBot.data.user.User;
import javaSpring.waterMeterTelegramBot.repository.exeption.FileException;
import javaSpring.waterMeterTelegramBot.repository.user.SaverUser;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class SaveFileUser implements SaverUser {
    Path pathCatalog;

    public SaveFileUser(Path pathCatalog) {
        this.pathCatalog = pathCatalog;
    }

    private void createDirectoriesIfNotExists(Path userFile) {
        Path directory = userFile.getParent();
        System.out.println("Путь проверки " + directory);
        if(directory != null && !Files.isDirectory(directory)){
            try {
                Files.createDirectories(directory);
            } catch (IOException e) {
                throw new FileException(e, "Ошибка при создании пути в классе: " + this.getClass().getSimpleName());
            }
        }
    }

    public Path pathForUser(String nameProfile, String nameUser){
        return Path.of( pathCatalog.toString() +  File.separator + nameProfile + File.separator +nameUser +".json");
    }

    @Override
    public void save(Profile profile, User user) {
        Path pathUser = pathForUser(profile.name(), user.name());
        createDirectoriesIfNotExists(pathUser);


        ObjectMapper mapper = new ObjectMapper();

        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        //mapper.enable(SerializationFeature.INDENT_OUTPUT); //разрешить перенос по строкам
        try {
            mapper.writeValue(pathUser.toFile(), user);
        } catch (IOException e) {
            throw new FileException(e, "Исключние при записи User в файл, класс: " + this.getClass().getSimpleName());
        }
    }
}
