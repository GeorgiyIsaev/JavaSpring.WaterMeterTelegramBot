package javaSpring.waterMeterTelegramBot.repository.user;

import javaSpring.waterMeterTelegramBot.data.profile.Profile;
import javaSpring.waterMeterTelegramBot.data.user.User;

public interface SaverUser {
    void save(User user);
}
