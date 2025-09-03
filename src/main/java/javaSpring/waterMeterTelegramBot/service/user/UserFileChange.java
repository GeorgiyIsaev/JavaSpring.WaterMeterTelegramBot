package javaSpring.waterMeterTelegramBot.service.user;

import javaSpring.waterMeterTelegramBot.data.user.User;
import javaSpring.waterMeterTelegramBot.data.user.WaterDrunk;
import javaSpring.waterMeterTelegramBot.data.user.WaterDrunksForDay;
import javaSpring.waterMeterTelegramBot.repository.store.UsersStore;
import javaSpring.waterMeterTelegramBot.repository.user.LoaderUsers;
import javaSpring.waterMeterTelegramBot.repository.user.SaverUser;
import org.springframework.beans.factory.annotation.Qualifier;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserFileChange implements UserChange{

    private final UsersStore usersStore;
    private final SaverUser saverUser;

    public UserFileChange(@Qualifier("usersInMemoryStore") UsersStore usersStore,
                              @Qualifier("loadFromFileUsers") LoaderUsers loaderUser,
                              @Qualifier("saveFileUser") SaverUser saverUser) {
        this.usersStore = usersStore;
        this.saverUser = saverUser;
        loaderUser.load(usersStore.getUsers());
    }


    public void save(User user){
        if(user !=null) {
            this.saverUser.save(user);
        }
    }


    @Override
    public User getUser(String nameUser) {
       User user = usersStore.getUser(nameUser);
       this.save(user);
       return user;
    }

    @Override
    public User createUser(String nameUser, int weight) {
        User user = usersStore.createUser(nameUser, weight);
        this.save(user);
        return user;
    }

    @Override
    public User getUserOrCreateIfNot(String nameUser) {
        User user = usersStore.getUserOrCreateIfNot(nameUser);
        this.save(user);
        return user;
    }

    @Override
    public User setWeightToUser(String nameUser, int newWeight) {
        User user = usersStore.setWeightToUser(nameUser, newWeight);
        this.save(user);
        return user;
    }

    @Override
    public User drunkWater (String nameUser, int countWaterMl){
        User user = this.getUser(nameUser);
        if(user == null) {
            return null;
        }
        LocalDateTime localDateTimeNow = LocalDateTime.now();
        WaterDrunk waterDrunk = new WaterDrunk(localDateTimeNow, countWaterMl);

        WaterDrunksForDay waterDrunksForDay = getTodayDate(user);
        waterDrunksForDay.waterDunks().add(waterDrunk);
        this.save(user);
        return user;
    }

    public WaterDrunksForDay getTodayDate(User user){
        LocalDate localDateNow = LocalDate.now();
        WaterDrunksForDay waterDrunksForDay;

        if(user.calendarWaterDrunk().isEmpty()){
            return createNewDayDrunk(user,localDateNow);
        }

        waterDrunksForDay = user.calendarWaterDrunk().getLast();
        if(!waterDrunksForDay.date().equals(localDateNow)){
            waterDrunksForDay = createNewDayDrunk(user,localDateNow);
        }

        return waterDrunksForDay;
    }
    private WaterDrunksForDay createNewDayDrunk(User user, LocalDate localDateNow){
        List<WaterDrunk> waterDunks = new ArrayList<>();
        WaterDrunksForDay waterDrunksForDay = new WaterDrunksForDay(localDateNow, waterDunks);
        user.calendarWaterDrunk().add(waterDrunksForDay);
        return waterDrunksForDay;
    }

    @Override
    public int countDrunkForToday(String nameUser){
        User user = this.getUser(nameUser);
        if(user == null) {
            return -1;
        }
        WaterDrunksForDay waterDrunksForDay = getTodayDate(user);
        int countWater = 0;
        for (WaterDrunk waterDrunk : waterDrunksForDay.waterDunks()){
            countWater += waterDrunk.countWaterMl();
        }
        return countWater;
    }
}
