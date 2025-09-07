package javaSpring.waterMeterTelegramBot.repository.profile.files;

import javaSpring.waterMeterTelegramBot.data.profile.Profile;

import java.util.Map;

public interface LoaderProfiles {
    void load(Map<String, Profile> profiles);
}
