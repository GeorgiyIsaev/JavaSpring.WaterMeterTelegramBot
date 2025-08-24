package javaSpring.waterMeterTelegramBot.consoleMenu.commands.userCommands;

import javaSpring.waterMeterTelegramBot.consoleMenu.ConsoleManager;
import javaSpring.waterMeterTelegramBot.consoleMenu.commands.CommandBase;
import javaSpring.waterMeterTelegramBot.waterMeterManager.User;
import javaSpring.waterMeterTelegramBot.waterMeterManager.WaterConsumption;
import javaSpring.waterMeterTelegramBot.waterMeterManager.WaterConsumptionCalendar;

import java.util.Date;

public class InfoUser extends CommandBase {

    public InfoUser(String name, ConsoleManager consoleManager) {
        super(name, consoleManager);
    }

    @Override
    public String description() {
        return "\"" + getName() + " 'имя пользователя'\" – Выводит информацию о пользователе";
    }

    @Override
    public String start(String message) {
        String[] splited = message.split(" ");
        if(splited.length <2){
            return "Ошибка передачи данных об имени пользователя";
        }
        User user = getConsoleManager().getUsersManager().getUser(splited[1]);
        if (user == null){
            return "Пользователь " + splited[1] + "не найден!";
        }
        return infoUser(user);
    }
    public String infoUser(User user){
        StringBuilder message = new StringBuilder();
        message.append("Пользователь: ").append(user.getName()).append(" ВЕС: ").append(user.getWeight()).append("\n");

        for (WaterConsumptionCalendar waterConsumptionCalendar : user.getCalendars()){
            message.append("День: ").append(waterConsumptionCalendar.getDate());
            message.append(" Всего Выпито: ").append(waterConsumptionCalendar.totalWaterDrunkForDay());
        }
        message.append("'\nОтметки сегодня: \n");
        WaterConsumptionCalendar presentDayDrunkWater = user.getCalendars().getLast();
        for (WaterConsumption drunk : presentDayDrunkWater.getConsumes()){
            message.append("Время: ").append(drunk.date().getHours()).append(":").append(drunk.date().getMinutes());
            message.append(" Выпито: ").append(drunk.countWaterMl()).append("\n");
        }
        return message.toString();
    }

}