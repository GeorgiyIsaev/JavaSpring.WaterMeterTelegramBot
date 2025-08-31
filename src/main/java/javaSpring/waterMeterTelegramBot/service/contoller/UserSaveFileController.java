package javaSpring.waterMeterTelegramBot.service.contoller;

import javaSpring.waterMeterTelegramBot.data.user.User;
import javaSpring.waterMeterTelegramBot.repository.user.SaverUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UserSaveFileController implements UserController {
    private final UserController userController;
    private final SaverUser saverUser;

    @Autowired
    public UserSaveFileController(
            @Qualifier("userChangeController") UserController userController,
            @Qualifier("saveFileUser") SaverUser saverUser){
        this.userController = userController;
        this.saverUser = saverUser;
    }


    @Override
    public void drunkWater(User user, int countWaterMl) {
       userController.drunkWater(user, countWaterMl);
       saverUser.save(user);
    }

    @Override
    public int countDrunkForDay(User user) {
        int countWaterDrunkML = userController.countDrunkForDay(user);
        saverUser.save(user);
        return countWaterDrunkML;
    }
}
