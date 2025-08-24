package javaSpring.waterMeterTelegramBot.consoleMenu.commands;

public interface ICommand {

    String getName();
    String description();
    String start(String message);
}
