package javaSpring.waterMeterTelegramBot.service.MY_TEST_wrapper;

import java.util.HashMap;
import java.util.Map;


//implements UsersStore
public class UsersWrappersInMemoryStory {
    private Map<String, UserWrapper> users;

    public UsersWrappersInMemoryStory() {
        this.users = new HashMap<>();
    }

//@Override
    public UserWrapper getUserOrCreateIfNot(String nameUser){
        UserWrapper user = getUser(nameUser);
        if(user == null){
            return createUser(nameUser, 0);
        }
        return user;
    }
  //  @Override
    public UserWrapper getUser(String nameUser){
        return users.get(nameUser);
    }
  //  @Override
    public UserWrapper createUser(String nameUser, int weight){
        UserWrapper user = new UserWrapper(nameUser, weight);
        users.put(nameUser, user);
        return user;
    }

    //изменение веса пользоватля теперь осуществляется во врапере
}

