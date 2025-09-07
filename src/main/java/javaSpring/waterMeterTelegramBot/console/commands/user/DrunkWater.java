package javaSpring.waterMeterTelegramBot.console.commands.user;


import javaSpring.waterMeterTelegramBot.console.commands.base.Command;
import javaSpring.waterMeterTelegramBot.data.user.User;
import javaSpring.waterMeterTelegramBot.service.user.UserChange;

public class DrunkWater implements Command {

    private final String name;
    private final UserChange userChange;

    public DrunkWater(String name, UserChange userChange) {
        this.name = name;
        this.userChange = userChange;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String description(){
        return  "\"" + getName() + "'имя пользовтеля' 'количество воды'\" – запись о выпитой воде в данный момент";
    }

    @Override
    public String start(String message) {
        String[] split = message.split(" ");
        if(split.length <3){
            return "Ошибка передачи данных о выпитой воде! Неполное сообщение";
        }
        int drinkWater;
        try {
            drinkWater = Integer.parseInt(split[2]);
        } catch (NumberFormatException e) {
            return "Ошибка передачи данных о выпитой воде! Значение: " + split[2] + " не число!";
        }
        User user = userChange.drunkWater(split[1],drinkWater);
        if(user == null){
            return "Пользователь "+split[1]+" не найден!";
        }
        return user.name() + " выпил " + drinkWater + " мл воды";
    }
}
