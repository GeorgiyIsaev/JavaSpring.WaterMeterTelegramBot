package javaSpring.waterMeterTelegramBot.service.user;

import javaSpring.waterMeterTelegramBot.data.user.User;
import javaSpring.waterMeterTelegramBot.data.user.WaterDrunk;
import javaSpring.waterMeterTelegramBot.data.user.WaterDrunksForDay;
import javaSpring.waterMeterTelegramBot.repository.store.UsersStore;
import javaSpring.waterMeterTelegramBot.repository.user.LoaderUsers;
import javaSpring.waterMeterTelegramBot.repository.user.SaverUser;
import javaSpring.waterMeterTelegramBot.service.MY_TEST_wrapper.UsersWrappersStore;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserInMemoryChange implements UserChange{

    private final UsersStore usersStore;

    public UserInMemoryChange(@Qualifier("usersInMemoryStore") UsersStore usersStore) {
        this.usersStore = usersStore;
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
    public User drunkWater (String nameUser, int countWaterMl){
        User user = this.getUser(nameUser);
        if(user == null) {
           return null;
        }
        LocalDateTime localDateTimeNow = LocalDateTime.now();
        WaterDrunk waterDrunk = new WaterDrunk(localDateTimeNow, countWaterMl);

        WaterDrunksForDay waterDrunksForDay = getTodayDate(user);
        waterDrunksForDay.waterDunks().add(waterDrunk);
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
