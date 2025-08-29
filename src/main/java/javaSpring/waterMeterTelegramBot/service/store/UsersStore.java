package javaSpring.waterMeterTelegramBot.service.store;


import javaSpring.waterMeterTelegramBot.data.user.User;

public interface UsersStore {
    User getUserOrCreateIfNot(String nameUser);
    User getUser(String nameUser);
    User createUser(String nameUser, int weight);
    User setWeightToUser(User user, int weight);
}
