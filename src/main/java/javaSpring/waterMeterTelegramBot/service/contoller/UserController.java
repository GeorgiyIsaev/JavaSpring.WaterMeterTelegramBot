package javaSpring.waterMeterTelegramBot.service.contoller;

import javaSpring.waterMeterTelegramBot.data.user.User;
import javaSpring.waterMeterTelegramBot.data.user.WaterDrunk;
import javaSpring.waterMeterTelegramBot.data.user.WaterDrunksForDay;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public interface UserController {

    void drunkWater (User user, int countWaterMl);
    int countDrunkForDay(User user);
}
