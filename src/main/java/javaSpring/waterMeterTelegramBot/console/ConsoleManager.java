package javaSpring.waterMeterTelegramBot.console;

import jakarta.annotation.PostConstruct;
import javaSpring.waterMeterTelegramBot.console.commands.base.Command;
import javaSpring.waterMeterTelegramBot.console.utils.ApplicationShutdownManager;
import javaSpring.waterMeterTelegramBot.data.profile.Profile;
import javaSpring.waterMeterTelegramBot.service.profile.ProfileSelect;
import javaSpring.waterMeterTelegramBot.console.utils.ConsoleController;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ConsoleManager {
    private final ConsoleController consoleController;
    private final ProfileSelect profileSelect;
    private final Commands commands;
    private final ApplicationShutdownManager applicationShutdownManager;

    public ConsoleManager(
            //@Qualifier("profileSelectFromConsole")
           // @Qualifier("defaultProfile")
            ProfileSelect profileSelect,
            ConsoleController consoleController,
            Commands commands,
            ApplicationShutdownManager applicationShutdownManager) {

        this.profileSelect = profileSelect;
        this.consoleController = consoleController;
        this.commands = commands;
        this.applicationShutdownManager = applicationShutdownManager;
    }

    @PostConstruct
    public void run() {
        hello();
        while (true) {
            String message = consoleController.input("Введите команду: ");
            String textCommand = extractCommand(message);
            if (isExit(textCommand)) {
                break;
            }
            runCommand(textCommand, message);
        }
        exit();
    }

    public boolean isExit(String command) {
        return command.equalsIgnoreCase("exit");
    }

    public void hello() {
        System.out.println("Приложение запущено!");
        Profile profile = profileSelect.currentProfile();
        System.out.println("Активный профиль: " + profile.name() + " Токен: " + profile.token());
        System.out.println("Пожалуйста используйте следующий шаблон:");
        System.out.println("'Команда' 'Имя пользователя' 'Сообщение'");
    }

    private void exit() {
        applicationShutdownManager.initiateShutdown(0);
        System.out.println("Работа завершена!");
    }

    public String extractCommand(String message) {
        return message.replaceAll(" .*", "");
    }

    public void runCommand(String textCommand, String message) {
        Command commandCall = commands.getCommand(textCommand);
        if (commandCall != null) {
            String outputMessage = commandCall.start(message);
            System.out.println(outputMessage);
        } else {
            System.out.println("Команда \"" + textCommand + "\" не распознана!");
        }

    }
}
