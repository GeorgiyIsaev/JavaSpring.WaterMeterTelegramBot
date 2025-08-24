package javaSpring.waterMeterTelegramBot.waterMeterManager;

import javaSpring.waterMeterTelegramBot.profiles.Profile;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class WaterMeterManager {

    private final Profile profile;

    Map<String,User> users;

    public WaterMeterManager(Profile profile) {
        this.profile = profile;
        System.out.println("Конструктор Выбран Профиль: {"+ profile.name() + " - " + profile.key() + "}");
        users = new HashMap<>();
    }

    public User getUser(String nameUser){
        return users.get(nameUser);
    }
    public User createUser(String nameUser, double weight){
        return users.put(nameUser, new User(nameUser, weight));
    }


}
