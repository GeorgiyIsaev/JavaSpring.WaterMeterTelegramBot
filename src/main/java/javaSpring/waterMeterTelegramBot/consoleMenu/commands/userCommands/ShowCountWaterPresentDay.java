package javaSpring.waterMeterTelegramBot.consoleMenu.commands.userCommands;

import javaSpring.waterMeterTelegramBot.consoleMenu.ConsoleManager;
import javaSpring.waterMeterTelegramBot.consoleMenu.commands.CommandBase;
import javaSpring.waterMeterTelegramBot.waterMeterManager.User;

import java.util.Date;

public class ShowCountWaterPresentDay extends CommandBase {

    public ShowCountWaterPresentDay(String name, ConsoleManager consoleManager) {
        super(name, consoleManager);
    }

    @Override
    public String description() {
        return "\"" + getName() + " 'имя пользователя'\" – вернет информацию о количестве выпитой сегодня воды";
    }

    @Override
    public String start(String message) {
        String[] splited = message.split(" ");
        if(splited.length <2){
            return "Ошибка передачи данных при выведении иформации о количестве выпитой сегодня воды";
        }
        User user = getConsoleManager().getUsersManager().getUser(splited[1]);
        if (user == null){
            return "Пользователь " + splited[1] + "не найден!";
        }
        return user.getName() + " выпил сегодня " + user.returnPresentDay(new Date()).totalWaterDrunkForDay();
    }
}
