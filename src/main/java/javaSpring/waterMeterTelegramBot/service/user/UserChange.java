package javaSpring.waterMeterTelegramBot.service.user;

import javaSpring.waterMeterTelegramBot.data.user.User;
import javaSpring.waterMeterTelegramBot.service.MY_TEST_wrapper.UserWrapper;

public interface UserChange {

    User getUser(String nameUser);
    User createUser(String nameUser, int weight);
    User getUserOrCreateIfNot(String nameUser);

    User setWeightToUser(String nameUser, int weight);
    User drunkWater(String nameUser, int drunkCountWaterMl);

    int countDrunkForToday(String nameUser);
}
