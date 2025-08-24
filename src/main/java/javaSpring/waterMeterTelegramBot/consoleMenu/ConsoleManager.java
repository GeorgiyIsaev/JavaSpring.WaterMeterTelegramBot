package javaSpring.waterMeterTelegramBot.consoleMenu;


import javaSpring.waterMeterTelegramBot.consoleMenu.commands.Exit;
import javaSpring.waterMeterTelegramBot.consoleMenu.commands.Help;
import javaSpring.waterMeterTelegramBot.consoleMenu.commands.ICommand;
import javaSpring.waterMeterTelegramBot.utils.ConsoleController;
import javaSpring.waterMeterTelegramBot.waterMeterManager.WaterMeterManager;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class ConsoleManager {
    private final Map<String, ICommand> commands;
    private final WaterMeterManager waterMeterManager;
    private boolean isExit = true;
    private final ConsoleController consoleController;

    public ConsoleManager(WaterMeterManager waterMeterManager, ConsoleController consoleController) {
        this.waterMeterManager = waterMeterManager;
        this.commands = new LinkedHashMap<>();
        this.consoleController = consoleController;
        addCommand(new Help("Help", this));
        addCommand(new Exit("Exit", this));
    }
    public void addCommand(ICommand iCommand){
        commands.put(iCommand.getName().toLowerCase(), iCommand);
    }



    public void setExit(boolean exit) {
        isExit = exit;
    }



    public Map<String, ICommand> getCommands() {
        return commands;
    }



    public void commandCall(String command){
        String cleanCommand = cleanupCommand(command);
        ICommand iCommand =  commands.get(cleanCommand);
        if(iCommand == null){
            System.out.println("Команда " + cleanCommand + " не найдена!");
            return;
        }
        String message = iCommand.start(command);
        System.out.println(message);
    }

    public String cleanupCommand(String command){
        int index = command.indexOf(' ');
        if (index >0){
            return command.substring(0, command.indexOf(' ')).toLowerCase();
        }
       return command.toLowerCase();

    }

    public void run(){
        while(this.isExit) {
            String command = consoleController.input("Введите команду: ");
            commandCall(command);
        }
    }
}
