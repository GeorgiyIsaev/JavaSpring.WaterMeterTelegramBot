package javaSpring.waterMeterTelegramBot.service.contoller;

import javaSpring.waterMeterTelegramBot.data.user.User;

import javaSpring.waterMeterTelegramBot.storingUserData.SaveFileUser;

public class UserSaveFileController implements UserController {
    private final UserController userController;
    private final SaveFileUser saveFileUser;
    public UserSaveFileController(UserChangeController userController, SaveFileUser saveFileUser) {
        this.userController = userController;
        this.saveFileUser = saveFileUser;
    }


    @Override
    public void drunkWater(User user, int countWaterMl) {
       userController.drunkWater(user, countWaterMl);
       saveFileUser.save(user);
    }

    @Override
    public int countDrunkForDay(User user) {
        int countWaterDrunkML = userController.countDrunkForDay(user);
        saveFileUser.save(user);
        return countWaterDrunkML;
    }
}
