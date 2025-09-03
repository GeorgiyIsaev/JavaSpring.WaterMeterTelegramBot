package javaSpring.waterMeterTelegramBot.repository.store;


import javaSpring.waterMeterTelegramBot.data.user.User;

import java.util.Map;

public interface UsersStore {
    Map<String, User> getUsers();

    User getUserOrCreateIfNot(String nameUser);
    User getUser(String nameUser);
    User createUser(String nameUser, int weight);
    User setWeightToUser(String nameUser, int weight);
}
