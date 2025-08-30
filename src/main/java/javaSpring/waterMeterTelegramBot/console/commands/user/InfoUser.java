package javaSpring.waterMeterTelegramBot.console.commands.user;

import javaSpring.waterMeterTelegramBot.console.commands.base.ICommand;
import javaSpring.waterMeterTelegramBot.data.user.User;

public class InfoUser implements ICommand {
    private final String name;

    public InfoUser(String name) {
        this.name = name;
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
    public String start(User user, String[] message) {
        if (user != null){
            return user.fullInfoUser();
        }
        return "Пользователь не найден!";
    }
}