package javaSpring.waterMeterTelegramBot.repository.profile.files;

import javaSpring.waterMeterTelegramBot.data.profile.Profile;

public interface SaverProfile {
    void save(Profile profile);
}
