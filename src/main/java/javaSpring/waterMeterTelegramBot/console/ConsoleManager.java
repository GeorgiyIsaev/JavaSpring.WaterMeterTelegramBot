package javaSpring.waterMeterTelegramBot.console;

import jakarta.annotation.PostConstruct;
import javaSpring.waterMeterTelegramBot.console.commands.base.Help;
import javaSpring.waterMeterTelegramBot.console.commands.base.ICommand;
import javaSpring.waterMeterTelegramBot.console.commands.user.DrunkWater;
import javaSpring.waterMeterTelegramBot.console.commands.user.InfoUser;
import javaSpring.waterMeterTelegramBot.console.commands.user.SetUserWeight;
import javaSpring.waterMeterTelegramBot.console.commands.user.ShowCountWaterPresentDay;
import javaSpring.waterMeterTelegramBot.data.profile.Profile;
import javaSpring.waterMeterTelegramBot.data.user.User;
import javaSpring.waterMeterTelegramBot.service.contoller.UserController;
import javaSpring.waterMeterTelegramBot.service.contoller.UserSaveFileController;
import javaSpring.waterMeterTelegramBot.service.store.profile.ProfilesStore;
import javaSpring.waterMeterTelegramBot.service.store.user.UsersFileStore;
import javaSpring.waterMeterTelegramBot.service.store.user.UsersStore;
import javaSpring.waterMeterTelegramBot.utils.ConsoleController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class ConsoleManager {
    private final ConsoleController consoleController;
    private final ProfilesStore profilesStore;
    private final UsersStore usersStore;
    private final Map<String, ICommand> commands;

    @Autowired
    public ConsoleManager(@Qualifier("userSaveFileController") UserController userController,
                          @Qualifier("usersFileStore") UsersStore usersStore,
                          @Qualifier("profileSelectFromConsole") ProfilesStore profilesStore,
                          ConsoleController consoleController){
        this.usersStore = usersStore;
        this.profilesStore = profilesStore;
        this.consoleController = consoleController;
        this.commands = new LinkedHashMap<>();
        generateCommand(userController);
    }

    private void generateCommand(UserController userController) {
        addCommand(new Help("Help", commands));
        addCommand(new DrunkWater("Выпил", userController));
        addCommand(new InfoUser("Показать"));
        addCommand(new SetUserWeight("Вес", usersStore));
        addCommand(new ShowCountWaterPresentDay("Сегодня", userController));
    }
    public void addCommand(ICommand iCommand){
        commands.put(iCommand.getName().toLowerCase(), iCommand);
    }


    @PostConstruct
    public void run(){
        hello();
        while(true) {
            String message = consoleController.input("Введите команду: ");
            ParseCommand parseCommand = parseCommand(message);
            if(isExit(parseCommand.nameCommand())){
                break;
            }

            ICommand commandCell = commands.get(parseCommand.nameCommand().toLowerCase());
            if (commandCell != null){
                String outputMessage = commandCell.start(parseCommand.user(), parseCommand.message());
                System.out.println(outputMessage);
            }
            else {
                System.out.println("Команда \"" + parseCommand.nameCommand() + "\" не распознана!");
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
        System.out.println("Работа завершена!");
    }


    public ParseCommand parseCommand (String messageFull){
        String[] message = messageFull.split(" ");
        String command = message[0];
        User user = null;
        if(message.length >1){
            user = usersStore.getUserOrCreateIfNot(message[1]);
        }
        return new ParseCommand(command, user, message);
    }
}
