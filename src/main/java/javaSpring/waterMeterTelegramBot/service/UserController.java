package javaSpring.waterMeterTelegramBot.service;

import javaSpring.waterMeterTelegramBot.data.user.User;
import javaSpring.waterMeterTelegramBot.data.user.WaterDrunk;
import javaSpring.waterMeterTelegramBot.data.user.WaterDrunksForDay;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserController {

    public User createUser(String name, int weight){
        List<WaterDrunksForDay> calendarWaterDrunk = new ArrayList<>();
        User user = new User(name,weight, calendarWaterDrunk);
        return user;
    }

    public String infoUser (User user){
        return "Пользователь" + user.name() + " Вес: " + user.weight();
    }

    public void drunkWater (User user, int countWaterMl){
        LocalDateTime localDateTimeNow = LocalDateTime.now();
        LocalDate localDateNow = LocalDate.now();
        WaterDrunk waterDrunk = new WaterDrunk(localDateTimeNow, countWaterMl);

        WaterDrunksForDay waterDrunksForDay = getTodayDate(user);
        waterDrunksForDay.waterDunks().add(waterDrunk);
    }

    public WaterDrunksForDay getTodayDate(User user){
        LocalDate localDateNow = LocalDate.now();
        WaterDrunksForDay waterDrunksForDay;

        if(user.calendarWaterDrunk().isEmpty()){
            System.out.println("isEmpty");
            createNewDayDrunk(user,localDateNow);
        }
        waterDrunksForDay = user.calendarWaterDrunk().getLast();
        if(!waterDrunksForDay.date().equals(localDateNow)){
            System.out.println("!equals");
            createNewDayDrunk(user,localDateNow);
        }

        return waterDrunksForDay;
    }
    private WaterDrunksForDay createNewDayDrunk(User user, LocalDate localDateNow){
        List<WaterDrunk> waterDunks = new ArrayList<>();
        WaterDrunksForDay waterDrunksForDay = new WaterDrunksForDay(localDateNow, waterDunks);
        user.calendarWaterDrunk().add(waterDrunksForDay);
        return waterDrunksForDay;
    }

    public int countDrunkForDay(User user){
        WaterDrunksForDay waterDrunksForDay = getTodayDate(user);
        int countWater = 0;
        for (WaterDrunk waterDrunk : waterDrunksForDay.waterDunks()){
            countWater += waterDrunk.countWaterMl();
        }
        return countWater;
    }

}
