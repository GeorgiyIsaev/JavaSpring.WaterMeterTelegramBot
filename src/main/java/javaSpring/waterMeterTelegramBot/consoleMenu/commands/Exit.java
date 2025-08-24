package javaSpring.waterMeterTelegramBot.consoleMenu.commands;


import javaSpring.waterMeterTelegramBot.consoleMenu.ConsoleManager;

public class Exit extends CommandBase {
    public Exit(String name, ConsoleManager consoleManager) {
        super(name, consoleManager);
    }

    @Override
    public String description(){
        return  "\"" + getName() + "\" – завершить программу";
    }

    @Override
    public String start(String message) {
        getConsoleManager().setExit(false);
        return "Программа завершена";
    }
}