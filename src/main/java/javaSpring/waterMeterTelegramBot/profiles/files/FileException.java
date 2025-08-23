package javaSpring.waterMeterTelegramBot.profiles.files;

public class FileException extends RuntimeException {

    public FileException(Exception ex, String text) {
        super("FileException: ошибка при работе с файлом!\n ["+ ex.getMessage() +"]\n" + text);
    }
}
