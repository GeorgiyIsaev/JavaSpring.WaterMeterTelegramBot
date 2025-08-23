package javaSpring.waterMeterTelegramBot.profiles.files;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javaSpring.waterMeterTelegramBot.profiles.Profile;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class LoaderProfiles {


    public Profile loadProfile(Path pathFile){
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
        Profile profile;
        try {
            profile = mapper.readValue(pathFile.toFile(), Profile.class);
        } catch (IOException e) {
            throw new FileException(e, "Исключение при чтении файла");
        }
        return profile;
    }

    public void loadProfiles(Path pathDirectory, Map<String,Profile> profiles){
        if (!Files.exists(pathDirectory)) {
           return;
        }


        try (DirectoryStream<Path> files = Files.newDirectoryStream(pathDirectory)) {
            for (Path path : files) {
                if(path.toFile().isFile()) {
                    Profile profile = loadProfile(path);
                    profiles.put(profile.name(), profile);
                    //System.out.println(path);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



      ///  return new Profile("", "");
    }

}
