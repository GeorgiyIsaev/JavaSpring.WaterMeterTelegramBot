package javaSpring.waterMeterTelegramBot.console.commands.base;

public interface Command {
    String getName();
    String description();
    String start(String message);
}
