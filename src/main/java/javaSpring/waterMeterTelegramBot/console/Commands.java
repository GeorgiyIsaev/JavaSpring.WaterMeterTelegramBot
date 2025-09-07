package javaSpring.waterMeterTelegramBot.console;

import javaSpring.waterMeterTelegramBot.console.commands.base.Command;
import javaSpring.waterMeterTelegramBot.console.commands.base.Help;
import javaSpring.waterMeterTelegramBot.console.commands.user.DrunkWater;
import javaSpring.waterMeterTelegramBot.console.commands.user.InfoUser;
import javaSpring.waterMeterTelegramBot.console.commands.user.SetUserWeight;
import javaSpring.waterMeterTelegramBot.console.commands.user.ShowCountWaterPresentDay;
import javaSpring.waterMeterTelegramBot.service.user.UserChange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class Commands {
    private final Map<String, Command> commands;

    public Commands(@Qualifier("userFileChange")UserChange userChange) {
        this.commands = new LinkedHashMap<>();
        generateCommand(userChange);
    }

    private void generateCommand(UserChange userChange) {
        addCommand(new Help("Help", commands));
        addCommand(new DrunkWater("Выпил", userChange));
        addCommand(new InfoUser("Показать", userChange));
        addCommand(new SetUserWeight("Вес", userChange));
        addCommand(new ShowCountWaterPresentDay("Сегодня", userChange));
    }
    public void addCommand(Command command){
        commands.put(command.getName().toLowerCase(), command);
    }
    public Command getCommand(String command){
        return commands.get(command.toLowerCase());
    }

}
