package javaSpring.waterMeterTelegramBot.waterMeterManager;

import javaSpring.waterMeterTelegramBot.dataUser.User;

public interface IUsersManager {
    User getUserOrCreateIfNot(String nameUser);
    User getUser(String nameUser);
    User createUser(String nameUser, int weight);
    User createUser(String nameUser);

    User setWeightToUser(String nameUser, int weight);
    User waterDrunkUser(String nameUser, int countDrunkWaterMl);
}
