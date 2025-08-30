package javaSpring.waterMeterTelegramBot.console.commands.user;


import javaSpring.waterMeterTelegramBot.console.commands.base.ICommand;
import javaSpring.waterMeterTelegramBot.data.user.User;
import javaSpring.waterMeterTelegramBot.service.contoller.UserController;


public class ShowCountWaterPresentDay implements ICommand {

    private final String name;
    UserController userController;

    public ShowCountWaterPresentDay(String name, UserController userController) {
        this.name = name;
        this.userController = userController;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String description() {
        return "\"" + getName() + " 'имя пользователя'\" – вернет информацию о количестве выпитой сегодня воды";
    }

    @Override
    public String start(User user, String[] message) {
        if(user == null){
            return "Пользователь не найден!";
        }
        int countDrunkForDay = userController.countDrunkForDay(user);
        return user.name() + " выпил сегодня " + countDrunkForDay;
    }
}
