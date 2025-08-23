package javaSpring.waterMeterTelegramBot.profiles.files;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import javaSpring.waterMeterTelegramBot.profiles.Profile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SaverProfiles {
   // private static boolean inExistPath(Path pathFile){
   //     return pathFile.toFile().isFile();
  //  }
    private void createDirectoriesIfNotExists(Path pathFile) {
        Path directory = pathFile.getParent();
        if(directory != null && !Files.isDirectory(directory)){
            try {
                Files.createDirectories(directory);
            } catch (IOException e) {
                throw new FileException(e, "Исключение при создании папки");
            }
        }
    }
    public void write(Path pathFile, Profile profile){
        createDirectoriesIfNotExists(pathFile);
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT); //разрешить перенос по строкам
        try {
            mapper.writeValue(pathFile.toFile(), profile);
        } catch (IOException e) {
            throw new FileException(e, "Исключение при записи в файл");
        }
    }

}
