package javaSpring.waterMeterTelegramBot.consoleMenu.commands.userCommands;

import javaSpring.waterMeterTelegramBot.consoleMenu.ConsoleManager;
import javaSpring.waterMeterTelegramBot.consoleMenu.commands.CommandBase;

public class SetUserWeight extends CommandBase {

    public SetUserWeight(String name, ConsoleManager consoleManager) {
        super(name, consoleManager);
    }

    @Override
    public String description() {
        return "\"" + getName() + " 'имя задачи'\" – добавить ЭПИК";
    }

    @Override
    public String start(String message) {


        return "";
    }


}