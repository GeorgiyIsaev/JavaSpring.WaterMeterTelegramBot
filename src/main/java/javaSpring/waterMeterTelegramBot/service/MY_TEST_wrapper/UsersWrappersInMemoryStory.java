package javaSpring.waterMeterTelegramBot.service.MY_TEST_wrapper;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class UsersWrappersInMemoryStory implements UsersWrappersStore{
    private final Map<String, UserWrapper> users;

    public UsersWrappersInMemoryStory() {
        this.users = new HashMap<>();
    }

    @Override
    public UserWrapper getUserOrCreateIfNot(String nameUser){
        UserWrapper user = getUser(nameUser);
        if(user == null){
            return createUser(nameUser, 0);
        }
        return user;
    }
    @Override
    public UserWrapper getUser(String nameUser){
        return users.get(nameUser);
    }
    @Override
    public UserWrapper createUser(String nameUser, int weight){
        UserWrapper user = new UserWrapper(nameUser, weight);
        users.put(nameUser, user);
        return user;
    }

    @Override
    public UserWrapper setWeightToUser(String nameUser, int weight) {
        UserWrapper user = getUser(nameUser);
        user.setWeight(weight);
        return user;
    }

    @Override
    public UserWrapper drunkWater(String nameUser, int drunkCountWaterMl) {
        UserWrapper user = getUser(nameUser);
        user.drunkWater(drunkCountWaterMl);
        return user;
    }
}




