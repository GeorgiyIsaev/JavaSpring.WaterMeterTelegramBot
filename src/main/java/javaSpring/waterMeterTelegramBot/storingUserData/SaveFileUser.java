//package javaSpring.waterMeterTelegramBot.storingUserData;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import javaSpring.waterMeterTelegramBot.profiles.files.FileException;
//import org.springframework.stereotype.Component;
//
//import java.io.File;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.io.StringWriter;
//import java.nio.file.Files;
//import java.nio.file.Path;
//
//@Component
//public class SaveFileUser {
//    Path pathCatalog;
//   // public SaveFileUser(Path pathCatalog) {
//   //     this.pathCatalog = pathCatalog;
// //   }
//    public SaveFileUser() {
//        this.pathCatalog = Path.of("data");
//    }
//
//    private void createDirectoriesIfNotExists(Path userFile) {
//        Path directory = userFile.getParent();
//        //System.out.println("Путь проверки " + directory);
//        if(directory != null && !Files.isDirectory(directory)){
//            try {
//                Files.createDirectories(directory);
//            } catch (IOException e) {
//                throw new FileException(e, "Ошибка при создании пути в классе: " + this.getClass().getSimpleName());
//            }
//        }
//    }
//
//    public Path pathForUser(String nameProfile, String nameUser){
//        return Path.of( pathCatalog.toString() +  File.separator + nameProfile + File.separator +nameUser +".json");
//    }
//
//    public void save(String nameProfile, String nameUser, User user) {
//        Path pathUser = pathForUser(nameProfile, nameUser);
//        createDirectoriesIfNotExists(pathUser);
//
//        try (PrintWriter pw = new PrintWriter(pathCatalog.toFile())) {
//           pw.write(convertJson(user));
//        } catch (IOException e) {
//            throw new FileException(e, "Ошибка при записи в классе: " + this.getClass().getSimpleName());
//        }
//    }
//
//    public String convertJson(User user){
//        StringWriter writer = new StringWriter();
//        ObjectMapper mapper = new ObjectMapper();
//        try {
//            mapper.writeValue(writer, user);
//        } catch (IOException e) {
//            throw new FileException(e, "Ошибка при конвертировании в JSON в классе: " + this.getClass().getSimpleName());
//        }
//       return writer.toString();
//    }
//
//
//
//
//
//}
