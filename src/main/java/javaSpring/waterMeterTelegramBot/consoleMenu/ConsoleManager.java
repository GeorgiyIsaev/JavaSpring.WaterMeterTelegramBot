package javaSpring.waterMeterTelegramBot.consoleMenu;


import javaSpring.waterMeterTelegramBot.consoleMenu.commands.Help;
import javaSpring.waterMeterTelegramBot.consoleMenu.commands.ICommand;
import javaSpring.waterMeterTelegramBot.utils.ConsoleController;
import javaSpring.waterMeterTelegramBot.waterMeterManager.WaterMeterManager;

import java.util.LinkedHashMap;
import java.util.Map;


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
        ICommand iCommand =  commands.get(command.toLowerCase());
        if(iCommand == null){
            System.out.println("Команда не найдена!");
            return;
        }
        iCommand.start("");
    }


    public void run(){
        while(this.isExit) {
            String command = consoleController.input("Введите команду: ");
            commandCall(command);
        }
    }
}
