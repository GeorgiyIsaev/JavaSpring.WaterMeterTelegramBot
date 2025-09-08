package javaSpring.waterMeterTelegramBot.service.profile;

import javaSpring.waterMeterTelegramBot.data.profile.Profile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(value="profile.default.enable" ,havingValue="true",matchIfMissing = false)
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
