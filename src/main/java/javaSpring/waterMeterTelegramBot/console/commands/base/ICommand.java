package javaSpring.waterMeterTelegramBot.console.commands.base;

import javaSpring.waterMeterTelegramBot.data.user.User;

public interface ICommand {
    String getName();
    String description();
    String start(User user, String[] message);
}
