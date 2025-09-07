package javaSpring.waterMeterTelegramBot.service.profile;

import javaSpring.waterMeterTelegramBot.data.profile.Profile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DefaultProfile implements ProfileSelect{

    private final Profile profile;

    public DefaultProfile(
            @Value("${defaultProfile.name}") String name,
            @Value("${defaultProfile.token}") String token){
        this.profile = new Profile(name,token);
    }

    @Override
    public Profile currentProfile() {
        return this.profile;
    }
}
