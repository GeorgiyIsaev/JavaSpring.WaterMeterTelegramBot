package javaSpring.waterMeterTelegramBot.service.MY_TEST_wrapper;

import javaSpring.waterMeterTelegramBot.data.user.User;

public interface UsersWrappersStore {
    UserWrapper getUserOrCreateIfNot(String nameUser);
    UserWrapper getUser(String nameUser);
    UserWrapper createUser(String nameUser, int weight);

    UserWrapper setWeightToUser(String nameUser, int weight);
    UserWrapper drunkWater(String nameUser, int drunkCountWaterMl);

}
