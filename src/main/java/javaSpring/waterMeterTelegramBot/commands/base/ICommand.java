package javaSpring.waterMeterTelegramBot.commands.base;

import javaSpring.waterMeterTelegramBot.data.user.User;

public interface ICommand {
    String getName();
    String description();
    String start(User user, String[] message);
}
