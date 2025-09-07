package javaSpring.waterMeterTelegramBot.service.user;

import javaSpring.waterMeterTelegramBot.data.user.User;
import javaSpring.waterMeterTelegramBot.repository.user.memory.UsersStore;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


//@Service
//@ConditionalOnProperty(
//      //  prefix ="userInMemoryChange",
//        value="memoryOnly")
////        ,havingValue="true",
////        matchIfMissing = false)
//@ConditionalOnProperties(prefix ="userInMemoryChange", value= "enabled", havingValue="true", mathIfMissing = false)


@Service
public final class UserInMemoryChange implements UserChange {
    private final UsersStore usersStore;
    private final DrunkWater drunkWater;
//
//    public UserInMemoryChange(UsersStore usersStore,
//                              DrunkWater drunkWater) {
//        this.usersStore = usersStore;
//        this.drunkWater = drunkWater;
//    }


    public UserInMemoryChange(@Qualifier("usersInMemoryStore") UsersStore usersStore,
                              DrunkWater drunkWater) {
        this.usersStore = usersStore;
        this.drunkWater = drunkWater;
    }

    @Override
    public User getUser(String nameUser) {
        return usersStore.getUser(nameUser);
    }

    @Override
    public User createUser(String nameUser, int weight) {
        return usersStore.createUser(nameUser, weight);
    }

    @Override
    public User getUserOrCreateIfNot(String nameUser) {
        return usersStore.getUserOrCreateIfNot(nameUser);
    }

    @Override
    public User setWeightToUser(String nameUser, int newWeight) {
        return usersStore.setWeightToUser(nameUser, newWeight);
    }

    @Override
    public User drunkWater(String nameUser, int countWaterMl) {
        User user = this.getUserOrCreateIfNot(nameUser);
        if (user == null) {
            return null;
        }
        this.drunkWater.drunkWater(user, countWaterMl);
        return user;
    }

    @Override
    public int countDrunkForToday(String nameUser) {
        User user = this.getUser(nameUser);
        if (user == null) {
            return -1;
        }
       return this.drunkWater.countDrunkForToday(user);
    }
}
