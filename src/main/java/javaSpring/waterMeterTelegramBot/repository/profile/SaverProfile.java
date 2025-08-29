package javaSpring.waterMeterTelegramBot.repository.profile;

import javaSpring.waterMeterTelegramBot.data.profile.Profile;

import java.nio.file.Path;

public interface SaverProfile {
    void save(Profile profile);
}
