package javaSpring.waterMeterTelegramBot.service.store.profile;

import javaSpring.waterMeterTelegramBot.data.profile.Profile;
import javaSpring.waterMeterTelegramBot.repository.profile.LoadFromFileProfiles;
import javaSpring.waterMeterTelegramBot.repository.profile.LoaderProfiles;
import javaSpring.waterMeterTelegramBot.repository.profile.SaveInFileProfile;
import javaSpring.waterMeterTelegramBot.repository.profile.SaverProfile;
import javaSpring.waterMeterTelegramBot.utils.ConsoleController;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ProfileSelectFromConsole implements ProfilesStore{
    private final Map<String, Profile> profiles;
    private final ConsoleController console;
    private final LoaderProfiles loaderProfiles;
    private final SaverProfile saverProfiles;

    public ProfileSelectFromConsole(ConsoleController console, LoaderProfiles loaderProfiles, SaverProfile saverProfiles) {
        this.console = console;
        this.loaderProfiles = loaderProfiles;
        this.saverProfiles = saverProfiles;
        this.profiles = new HashMap<>();
        loaderProfiles.load(this.profiles);
    }

    @Override
    public Profile currentProfile() {
        return null;
    }
}
