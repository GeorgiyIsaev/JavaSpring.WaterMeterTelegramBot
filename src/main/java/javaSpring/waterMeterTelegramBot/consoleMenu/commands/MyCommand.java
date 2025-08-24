package javaSpring.waterMeterTelegramBot.consoleMenu.commands;


import javaSpring.waterMeterTelegramBot.consoleMenu.ConsoleManager;

public class MyCommand extends CommandBase {

    public MyCommand(String name, ConsoleManager consoleManager) {
        super(name, consoleManager);
    }

    @Override
    public String description(){
        return  "\"" + getName() + " 'имя задачи'\" – добавить ЭПИК";
    }

    @Override
    public String start(String message) {
        return "";
    }


}
