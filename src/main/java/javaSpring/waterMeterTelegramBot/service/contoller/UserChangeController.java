package javaSpring.waterMeterTelegramBot.service.contoller;

import javaSpring.waterMeterTelegramBot.data.user.User;
import javaSpring.waterMeterTelegramBot.data.user.WaterDrunk;
import javaSpring.waterMeterTelegramBot.data.user.WaterDrunksForDay;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserChangeController implements UserController {




    @Override
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
           // System.out.println("isEmpty");
            return createNewDayDrunk(user,localDateNow);
        }
        waterDrunksForDay = user.calendarWaterDrunk().getLast();
        if(!waterDrunksForDay.date().equals(localDateNow)){
           // System.out.println("!equals");
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
    public int countDrunkForDay(User user){
        WaterDrunksForDay waterDrunksForDay = getTodayDate(user);
        int countWater = 0;
        for (WaterDrunk waterDrunk : waterDrunksForDay.waterDunks()){
            countWater += waterDrunk.countWaterMl();
        }
        return countWater;
    }
}
