package javaSpring.waterMeterTelegramBot.consoleMenu.commands.userCommands;


import javaSpring.waterMeterTelegramBot.consoleMenu.ConsoleManager;
import javaSpring.waterMeterTelegramBot.consoleMenu.commands.CommandBase;
import javaSpring.waterMeterTelegramBot.waterMeterManager.User;

public class DrunkWater extends CommandBase {

    public DrunkWater(String name, ConsoleManager consoleManager) {
        super(name, consoleManager);
    }

    @Override
    public String description(){
        return  "\"" + getName() + " 'количество воды' 'имя пользовтеля'\" – запись о выпитой воде в данный момент";
    }

    @Override
    public String start(String message) {
        String[] splited = message.split(" ");
        if(splited.length <2){
            return "Ошибка передачи данных о выпитой воде!";
        }

        int drinkWater;
        try {
            drinkWater = Integer.parseInt(splited[1]);
        } catch (NumberFormatException e) {
            return "Ошибка передачи данных о выпитой воде!";
        }
        User user = this.getConsoleManager().getUsersManager().getUserOrCreateIfNot(splited[2]);

        user.addDrunkWater(drinkWater);
        return user.getName() + " выпил " + drinkWater;
    }


}
