package javaSpring.waterMeterTelegramBot.repository.user.files;

import javaSpring.waterMeterTelegramBot.data.user.User;

public interface SaverUser {
    void save(User user);
}
