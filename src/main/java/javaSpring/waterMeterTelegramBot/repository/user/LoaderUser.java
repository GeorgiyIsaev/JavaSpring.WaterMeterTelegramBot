package javaSpring.waterMeterTelegramBot.repository.user;

import javaSpring.waterMeterTelegramBot.data.user.User;

public interface LoaderUser {
    User load(String nameUser);
}
