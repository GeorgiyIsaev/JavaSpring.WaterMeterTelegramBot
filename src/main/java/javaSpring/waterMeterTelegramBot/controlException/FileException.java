package javaSpring.waterMeterTelegramBot.controlException;

public class FileException extends ControlException {

    public FileException(Exception ex, String text) {
        super("FileException: ошибка при работе с файлом!\n ["+ ex.getMessage() +"]\n" + text);
    }
}
