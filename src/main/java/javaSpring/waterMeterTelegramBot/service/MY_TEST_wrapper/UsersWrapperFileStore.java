package javaSpring.waterMeterTelegramBot.service.MY_TEST_wrapper;

import javaSpring.waterMeterTelegramBot.data.user.User;
import javaSpring.waterMeterTelegramBot.repository.user.LoaderUsers;
import javaSpring.waterMeterTelegramBot.repository.user.SaverUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.HashMap;

public class UsersWrapperFileStore implements UsersWrappersStore{
    private final UsersWrappersStore usersWrappersStore;
    private final SaverUser saverUser;

    @Autowired
    public UsersWrapperFileStore(
            @Qualifier("usersWrappersInMemoryStory")     UsersWrappersStore usersWrappersStore,
            @Qualifier("saveFileUser") SaverUser saverUser,
            @Qualifier("loadFromFileUsers") LoaderUsers loaderUser) {
        this.saverUser = saverUser;
        this.usersWrappersStore = usersWrappersStore;
       // loaderUser.load(users); TODO переделать загрузку враперов с пользователем из файла
    }

    public void saveUser(User user){
        saverUser.save(user);
    }

    @Override
    public UserWrapper getUserOrCreateIfNot(String nameUser) {
        UserWrapper userWrapper = usersWrappersStore.getUserOrCreateIfNot(nameUser);
        saveUser(userWrapper.getUser());
        return userWrapper;
    }

    @Override
    public UserWrapper getUser(String nameUser) {
        UserWrapper userWrapper = usersWrappersStore.getUser(nameUser);
        if(userWrapper != null) {
            saveUser(userWrapper.getUser());
        }
        return userWrapper;
    }

    @Override
    public UserWrapper createUser(String nameUser, int weight) {
        UserWrapper userWrapper = usersWrappersStore.createUser(nameUser, weight);
        saveUser(userWrapper.getUser());
        return userWrapper;
    }

    @Override
    public UserWrapper setWeightToUser(String nameUser, int weight) {
        UserWrapper userWrapper = usersWrappersStore.setWeightToUser(nameUser, weight);
        saveUser(userWrapper.getUser());
        return userWrapper;
    }

    @Override
    public UserWrapper drunkWater(String nameUser, int drunkCountWaterMl) {
        UserWrapper userWrapper = usersWrappersStore.drunkWater(nameUser, drunkCountWaterMl);
        saveUser(userWrapper.getUser());
        return userWrapper;
    }
}
