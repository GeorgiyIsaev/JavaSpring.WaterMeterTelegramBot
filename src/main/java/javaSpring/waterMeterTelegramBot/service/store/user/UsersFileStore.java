package javaSpring.waterMeterTelegramBot.service.store.user;

import javaSpring.waterMeterTelegramBot.data.user.User;
import javaSpring.waterMeterTelegramBot.data.user.WaterDrunksForDay;
import javaSpring.waterMeterTelegramBot.repository.store.UsersStore;
import javaSpring.waterMeterTelegramBot.repository.user.LoaderUsers;
import javaSpring.waterMeterTelegramBot.repository.user.SaverUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UsersFileStore implements UsersStore {
    private final Map<String, User> users;
    private final SaverUser saverUser;

    @Autowired
    public UsersFileStore(
            @Qualifier("loadFromFileUsers") LoaderUsers loaderUser,
            @Qualifier("saveFileUser") SaverUser saverUser) {
        this.saverUser = saverUser;

        this.users = new HashMap<>();
        loaderUser.load(users);
    }

    public void save(User user){
        saverUser.save(user);
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
        this.save(user);
        return user;
    }

    ///  Изменение веса пользователя
    @Override
    public User setWeightToUser(User user, int weight){
        User changeuser = new User(user.name(), weight, user.calendarWaterDrunk());
        users.put(user.name(), changeuser);
        this.save(user);
        return user;
    }
}
