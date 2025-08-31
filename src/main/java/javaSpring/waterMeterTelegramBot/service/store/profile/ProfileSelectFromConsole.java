package javaSpring.waterMeterTelegramBot.service.store.profile;

import javaSpring.waterMeterTelegramBot.data.profile.Profile;
import javaSpring.waterMeterTelegramBot.repository.profile.LoaderProfiles;
import javaSpring.waterMeterTelegramBot.repository.profile.SaverProfile;
import javaSpring.waterMeterTelegramBot.service.contoller.UserController;
import javaSpring.waterMeterTelegramBot.utils.ConsoleController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.TreeMap;

@Service
public class ProfileSelectFromConsole implements ProfilesStore {
    private final Map<String, Profile> profiles;
    private final ConsoleController console;
    private final SaverProfile saverProfiles;

    private Profile currentProfile;

    @Autowired
    public ProfileSelectFromConsole(
            @Qualifier("loadFromFileProfiles") LoaderProfiles loaderProfiles,
            @Qualifier("saveInFileProfile") SaverProfile saverProfiles,
            ConsoleController console) {
        this.console = console;
        this.saverProfiles = saverProfiles;
        this.profiles = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

        loaderProfiles.load(this.profiles);
    }

    @Override
    public Profile currentProfile() {
        if (currentProfile == null) {
            selectedProfileFromConsole();
        }
        return currentProfile;
    }

    public void selectedProfileFromConsole() {
        this.printMenu();
        String input = console.input("Введите имя существующего профиля или создайте новый введя новое имя: ");
        this.currentProfile = getProfile(input);
        if (this.currentProfile == null) {
            this.currentProfile = createNewProfile(input);
        }
    }

    private void printMenu() {
        if (!profiles.isEmpty()) {
            System.out.println("Меню выбора профиля: ");
        }
        int count = 0;
        for (Profile profile : profiles.values()) {
            System.out.println(count++ + ": {" + profile.name() + " - " + profile.key() + "};");
        }
    }

    public Profile createNewProfile(String name) {
        System.out.println("Профиль с именем " + name + " не найден!");
        String key = console.input("Введите ключ для профиля " + name + ":");
        return this.addProfile(name, key);
    }

    public Profile addProfile(String name, String key) {
        name = deleteSpecialCharacters(name);
        Profile profile = new Profile(name, key);
        profiles.put(name, profile);
        saverProfiles.save(profile);
        return profile;
    }

    public String deleteSpecialCharacters(String text) {
        return text.replace("\\", "");
    }

    public Profile getProfile(String profileName) {
        return profiles.get(profileName);
    }
}
