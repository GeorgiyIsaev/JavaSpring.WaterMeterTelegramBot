package javaSpring.waterMeterTelegramBot.console.commands.user;


import javaSpring.waterMeterTelegramBot.console.commands.base.Command;
import javaSpring.waterMeterTelegramBot.service.user.UserChange;


public class ShowCountWaterPresentDay implements Command {

    private final String name;
    UserChange userChange;

    public ShowCountWaterPresentDay(String name, UserChange userChange) {
        this.name = name;
        this.userChange = userChange;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String description() {
        return "\"" + getName() + " 'имя пользователя'\" – вернет информацию о количестве выпитой сегодня воды";
    }

    @Override
    public String start(String message) {
        String[] split = message.split(" ");
        if (split.length < 2) {
            return "Ошибка передачи данных о выпитой воде! Неполное сообщение";
        }
        int countWaterToday = userChange.countDrunkForToday(split[1]);
        if (countWaterToday < 0) {
            return "Пользователь " + split[1] + " не найден!";
        }
        return "Пользователь " + split[1] + " выпил " + countWaterToday + " мл воды!";
    }
}
