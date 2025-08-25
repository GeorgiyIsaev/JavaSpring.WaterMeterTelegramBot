package javaSpring.waterMeterTelegramBot.waterMeterManager;

import javaSpring.waterMeterTelegramBot.profiles.Profile;
import javaSpring.waterMeterTelegramBot.dataUser.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UsersManager {
    private final Profile profile;
    Map<String, User> users;

    public UsersManager(Profile profile) {
        this.profile = profile;
        // System.out.println("Конструктор WaterMeterManager сообщает - Выбран Профиль: {"+ profile.name() + " - " + profile.key() + "}");
        users = new HashMap<>();
    }

    ///  Создание или вызов Пользователя
    public User getUserOrCreateIfNot(String nameUser){
        User user = getUser(nameUser);
        if(user == null){
            return createUser(nameUser);
        }
        return user;
    }
    public User getUser(String nameUser){
        return users.get(nameUser);
    }
    public User createUser(String nameUser, double weight){
        User user = new User(nameUser, weight);
        users.put(nameUser, user);
        return user;
    }
    public User createUser(String nameUser){
        User user = new User(nameUser, 0);
        users.put(nameUser, user);
        return user;
    }

    ///  Изменение веса пользователя
    public User setWeightToUser(String nameUser, double weight){
        User user = getUser(nameUser);
        if(user != null){
            user.setWeight(weight);
        }
        return user;
    }

    /// Запись информации о выпитой воде
    public User waterDrunkUser(String nameUser, int countDrunkWaterMl){
        User user = getUserOrCreateIfNot(nameUser);
        user.addDrunkWater(countDrunkWaterMl);
        return user;
    }


}
