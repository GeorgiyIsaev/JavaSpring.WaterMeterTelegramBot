package javaSpring.waterMeterTelegramBot.repository.store;

import javaSpring.waterMeterTelegramBot.data.user.User;
import javaSpring.waterMeterTelegramBot.data.user.WaterDrunksForDay;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UsersInMemoryStore implements UsersStore{

    private Map<String, User> users;

    public UsersInMemoryStore() {
        this.users = new HashMap<>();
    }

    @Override
    public User getUserOrCreateIfNot(String nameUser){
        User user = getUser(nameUser);
        if(user == null){
            return createUser(nameUser, 0);
        }
        return user;
    }

    @Override
    public User getUser(String nameUser){
        return users.get(nameUser);
    }

    @Override
    public User createUser(String nameUser, int weight){
        List<WaterDrunksForDay> calendarWaterDrunk = new ArrayList<>();
        User user = new User(nameUser, weight, calendarWaterDrunk);
        users.put(nameUser, user);
        return user;
    }

    ///  Изменение веса пользователя
    @Override
    public User setWeightToUser(User user, int weight){
        User changeuser = new User(user.name(), weight, user.calendarWaterDrunk());
        users.put(user.name(), changeuser);
        return user;
    }
}
