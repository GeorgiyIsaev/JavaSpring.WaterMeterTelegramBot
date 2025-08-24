package javaSpring.waterMeterTelegramBot.waterMeterManager;

import javaSpring.waterMeterTelegramBot.profiles.Profile;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UsersManager {

    private final Profile profile;

    Map<String,User> users;

    public UsersManager(Profile profile) {
        this.profile = profile;
        System.out.println("Конструктор WaterMeterManager сообщает - Выбран Профиль: {"+ profile.name() + " - " + profile.key() + "}");
        users = new HashMap<>();
    }

    public User getUserOrCreateIfNot(String nameUser){
        User user = getUser(nameUser);
        if(user == null){
            user = createUser(nameUser);
        }
        return user;
    }

    public User getUser(String nameUser){
        return users.get(nameUser);
    }
    public User createUser(String nameUser, double weight){
        return users.put(nameUser, new User(nameUser, weight));
    }
    public User createUser(String nameUser){
        return users.put(nameUser, new User(nameUser, 0));
    }

    public User setWeightToUser(String nameUser, double weight){
        User user = getUser(nameUser);
        if(user != null){
            user.setWeight(weight);
        }
        return user;
    }


}
