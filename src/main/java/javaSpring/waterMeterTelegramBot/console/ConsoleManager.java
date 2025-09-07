package javaSpring.waterMeterTelegramBot.console;

import jakarta.annotation.PostConstruct;
import javaSpring.waterMeterTelegramBot.console.commands.base.Help;
import javaSpring.waterMeterTelegramBot.console.commands.base.Command;
import javaSpring.waterMeterTelegramBot.console.commands.user.DrunkWater;
import javaSpring.waterMeterTelegramBot.console.commands.user.InfoUser;
import javaSpring.waterMeterTelegramBot.console.commands.user.SetUserWeight;
import javaSpring.waterMeterTelegramBot.console.commands.user.ShowCountWaterPresentDay;
import javaSpring.waterMeterTelegramBot.console.utils.ApplicationShutdownManager;
import javaSpring.waterMeterTelegramBot.data.profile.Profile;
import javaSpring.waterMeterTelegramBot.service.store.profile.ProfilesStore;
import javaSpring.waterMeterTelegramBot.console.utils.ConsoleController;
import javaSpring.waterMeterTelegramBot.service.user.UserChange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
//@ConditionalOnProperties(value="userconrol" )
public class ConsoleManager {
    private final ConsoleController consoleController;
    private final ProfilesStore profilesStore;;
    private final Map<String, Command> commands;
    ApplicationShutdownManager applicationShutdownManager;

    public ConsoleManager(@Qualifier("userFileChange")
                          UserChange userChange,
                          @Qualifier("profileSelectFromConsole")
                          ProfilesStore profilesStore,
                          ConsoleController consoleController,
                          ApplicationShutdownManager applicationShutdownManager){
        this.profilesStore = profilesStore;
        this.consoleController = consoleController;
        this.applicationShutdownManager = applicationShutdownManager;
        this.commands = new LinkedHashMap<>();
        generateCommand(userChange);
    }

    private void generateCommand(UserChange userChange) {
        addCommand(new Help("Help", commands));
        addCommand(new DrunkWater("Выпил", userChange));
        addCommand(new InfoUser("Показать", userChange));
        addCommand(new SetUserWeight("Вес", userChange));
        addCommand(new ShowCountWaterPresentDay("Сегодня", userChange));
    }
    public void addCommand(Command command){
        commands.put(command.getName().toLowerCase(), command);
    }


    @PostConstruct
    public void run(){
        hello();
        while(true) {
            String message = consoleController.input("Введите команду: ");
            String textCommand = extractCommand(message);
            if(isExit(textCommand)){
                break;
            }

            Command commandCell = commands.get(textCommand.toLowerCase());
            if (commandCell != null){
                String outputMessage = commandCell.start(message);
                System.out.println(outputMessage);
            }
            else {
                System.out.println("Команда \"" + textCommand + "\" не распознана!");
            }
        }
        exit();
    }

    public boolean isExit(String command){
        return command.equalsIgnoreCase("exit");
    }

    public void hello(){
        System.out.println("Приложение запущено!");
        Profile profile = profilesStore.currentProfile();
        System.out.println("Активный профиль: " + profile.name() + " Ключ: " + profile.key());
        System.out.println("Пожалуйста используйте следующий шаблон:" );
        System.out.println("'Команда' 'Имя пользователя' 'Сообщение'" );
    }

    private void exit() {
        applicationShutdownManager.initiateShutdown(0);
        System.out.println("Работа завершена!");
    }

    public String extractCommand(String message){
        return message.replaceAll(" .*", "");
    }
}
