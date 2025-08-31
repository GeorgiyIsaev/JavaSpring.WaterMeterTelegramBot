package javaSpring.waterMeterTelegramBot.console.utils;

import javaSpring.waterMeterTelegramBot.data.user.User;

public record ParseCommand(
        String nameCommand,
        User user,
        String[] message) {
}
