package javaSpring.waterMeterTelegramBot.consoleMenu.commands.userCommands;

import javaSpring.waterMeterTelegramBot.consoleMenu.ConsoleManager;
import javaSpring.waterMeterTelegramBot.consoleMenu.commands.CommandBase;
import javaSpring.waterMeterTelegramBot.waterMeterManager.User;



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
        String[] splited = message.split(" ");
        if(splited.length <2){
            return "Ошибка передачи данных при изменении веса!";
        }

        int weight;
        try {
            weight = Integer.parseInt(splited[1]);
        } catch (NumberFormatException e) {
            return "Ошибка передачи данных при изменении веса!";
        }

        User user = getConsoleManager().getUsersManager().setWeightToUser(splited[2], weight);
        return "Вес " + user.getName() + " составляет " + user.getWeight();
    }
}