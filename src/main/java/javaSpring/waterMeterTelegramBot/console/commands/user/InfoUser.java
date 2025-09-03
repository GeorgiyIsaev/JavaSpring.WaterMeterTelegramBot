package javaSpring.waterMeterTelegramBot.console.commands.user;

import javaSpring.waterMeterTelegramBot.console.commands.base.Command;
import javaSpring.waterMeterTelegramBot.data.user.User;
import javaSpring.waterMeterTelegramBot.service.user.UserChange;

public class InfoUser implements Command {
    private final String name;
    UserChange userChange;

    public InfoUser(String name, UserChange userChange) {
        this.name = name;
        this.userChange = userChange;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String description() {
        return "\"" + getName() + " 'имя пользователя'\" – Выводит информацию о пользователе";
    }

    @Override
    public String start(String message) {
        String[] split = message.split(" ");
        if(split.length <2){
            return "Ошибка передачи данных о выпитой воде! Неполное сообщение";
        }
        User user = userChange.getUser(split[1]);
        if(user == null){
            return "Пользователь "+split[1]+" не найден!";
        }
        return user.toString();
    }
}