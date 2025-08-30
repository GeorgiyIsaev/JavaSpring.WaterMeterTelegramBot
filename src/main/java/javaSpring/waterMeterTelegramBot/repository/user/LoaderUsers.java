package javaSpring.waterMeterTelegramBot.repository.user;

import javaSpring.waterMeterTelegramBot.data.user.User;

import java.util.Map;

public interface LoaderUsers {
    void load(Map<String, User> users);
    User loadUser(String nameUser);
}
