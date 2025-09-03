package javaSpring.waterMeterTelegramBot.service.user;

import javaSpring.waterMeterTelegramBot.data.user.User;
import javaSpring.waterMeterTelegramBot.repository.store.UsersStore;
import javaSpring.waterMeterTelegramBot.repository.user.LoaderUsers;
import javaSpring.waterMeterTelegramBot.repository.user.SaverUser;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service
public class UserFileChange implements UserChange {

    private final UsersStore usersStore;
    private final SaverUser saverUser;
    private final DrunkWater drunkWater;

    public UserFileChange(@Qualifier("usersInMemoryStore") UsersStore usersStore,
                          @Qualifier("loadFromFileUsers") LoaderUsers loaderUser,
                          @Qualifier("saveFileUser") SaverUser saverUser,
                          DrunkWater drunkWater) {
        this.usersStore = usersStore;
        this.saverUser = saverUser;
        this.drunkWater = drunkWater;
        loaderUser.load(usersStore.getUsers());
    }


    public void save(User user) {
        if (user != null) {
            this.saverUser.save(user);
        }
    }


    @Override
    public User getUser(String nameUser) {
        return usersStore.getUser(nameUser);
    }

    @Override
    public User createUser(String nameUser, int weight) {
        User user = usersStore.createUser(nameUser, weight);
        this.save(user);
        return user;
    }

    @Override
    public User getUserOrCreateIfNot(String nameUser) {
        User user = this.getUser(nameUser);
        if(user == null){
            return this.createUser(nameUser, 0);
        }
        return user;
     }

    @Override
    public User setWeightToUser(String nameUser, int newWeight) {
        User user = usersStore.setWeightToUser(nameUser, newWeight);
        this.save(user);
        return user;
    }

    @Override
    public User drunkWater(String nameUser, int countWaterMl) {
        User user = this.getUser(nameUser);
        if (user == null) {
            return null;
        }
        this.drunkWater.drunkWater(user, countWaterMl);
        this.save(user);
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
