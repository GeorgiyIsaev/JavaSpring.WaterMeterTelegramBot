package javaSpring.waterMeterTelegramBot.commands.user;


import javaSpring.waterMeterTelegramBot.commands.base.ICommand;
import javaSpring.waterMeterTelegramBot.data.user.User;
import javaSpring.waterMeterTelegramBot.service.contoller.UserController;

public class DrunkWater implements ICommand {

    private final String name;
    UserController userController;

    public DrunkWater(String name, UserController userController) {
        this.name = name;
        this.userController = userController;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String description(){
        return  "\"" + getName() + " 'количество воды' 'имя пользовтеля'\" – запись о выпитой воде в данный момент";
    }

    @Override
    public String start(User user, String[] message) {
        if(message.length <3){
            return "Ошибка передачи данных о выпитой воде!";
        }

        int drinkWater;
        try {
            drinkWater = Integer.parseInt(message[2]);
        } catch (NumberFormatException e) {
            return "Ошибка передачи данных о выпитой воде!";
        }

        if(user == null){
            return "Пользователь не найден!";
        }
        userController.drunkWater(user, drinkWater);
        return user.name() + " выпил " + drinkWater + " мл воды";
    }


}
