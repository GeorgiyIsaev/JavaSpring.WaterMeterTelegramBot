package javaSpring.waterMeterTelegramBot.service.contoller;

import javaSpring.waterMeterTelegramBot.data.user.User;

public interface UserController {

    void drunkWater (User user, int countWaterMl);
    int countDrunkForDay(User user);
}
