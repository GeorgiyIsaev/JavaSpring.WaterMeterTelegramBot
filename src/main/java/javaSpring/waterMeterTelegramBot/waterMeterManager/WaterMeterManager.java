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

}
