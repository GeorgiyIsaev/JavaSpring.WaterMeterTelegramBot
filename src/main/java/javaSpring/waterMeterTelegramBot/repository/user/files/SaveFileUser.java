package javaSpring.waterMeterTelegramBot.repository.user.files;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import javaSpring.waterMeterTelegramBot.data.user.User;
import javaSpring.waterMeterTelegramBot.repository.exeption.FileException;
import javaSpring.waterMeterTelegramBot.repository.utils.PathConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;

@Repository
@ConditionalOnProperty(value="user.inFile.enable" ,havingValue="true",matchIfMissing = false)
public class SaveFileUser implements SaverUser {
    private final Path pathCatalog;

    public SaveFileUser(PathConfiguration pathInit) {
        this.pathCatalog = pathInit.getPathUsers();
    }

    private void createDirectoriesIfNotExists(Path userFile) {
        Path directory = userFile.getParent();
        if(directory != null && !Files.isDirectory(directory)){
            try {
                Files.createDirectories(directory);
            } catch (IOException e) {
                throw new FileException(e, "Ошибка при создании пути в классе: " + this.getClass().getSimpleName());
            }
        }
    }

    public Path pathForUser(String nameUser){
        return Path.of( pathCatalog.toString() + File.separator +nameUser +".json");
    }

    @Override
    public void save(User user) {
        Path pathUser = pathForUser(user.name());
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
