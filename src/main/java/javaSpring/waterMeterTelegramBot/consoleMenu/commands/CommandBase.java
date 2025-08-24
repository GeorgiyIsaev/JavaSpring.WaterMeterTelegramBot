package javaSpring.waterMeterTelegramBot.consoleMenu.commands;


import javaSpring.waterMeterTelegramBot.consoleMenu.ConsoleManager;

public abstract class CommandBase implements ICommand{
    private final String name;
    private final ConsoleManager consoleManager;


    public CommandBase(String name, ConsoleManager consoleManager){
        this.name = name;
        this.consoleManager = consoleManager;
    }
    @Override
    public String getName() {
        return name;
    }

    public ConsoleManager getConsoleManager() {
        return consoleManager;
    }
}
