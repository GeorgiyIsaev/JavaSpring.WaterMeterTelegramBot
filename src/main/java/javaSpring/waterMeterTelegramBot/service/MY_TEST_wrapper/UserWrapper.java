package javaSpring.waterMeterTelegramBot.service.MY_TEST_wrapper;

import javaSpring.waterMeterTelegramBot.data.user.User;
import javaSpring.waterMeterTelegramBot.data.user.WaterDrunk;
import javaSpring.waterMeterTelegramBot.data.user.WaterDrunksForDay;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserWrapper {
    private User user;

    public UserWrapper(String nameUser, int weight) {
        List<WaterDrunksForDay> calendarWaterDrunk = new ArrayList<>();
        this.user = new User(nameUser, weight, calendarWaterDrunk);
    }

    public User getUser() {
        return user;
    }

    public void setWeight(int weight) {
        User changeuser = new User(user.name(), weight, user.calendarWaterDrunk());
        this.user = changeuser;
    }

    public void drunkWater(User user, int countWaterMl) {
        LocalDateTime localDateTimeNow = LocalDateTime.now();
        LocalDate localDateNow = LocalDate.now();
        WaterDrunk waterDrunk = new WaterDrunk(localDateTimeNow, countWaterMl);

        WaterDrunksForDay waterDrunksForDay = getTodayDate(user);
        waterDrunksForDay.waterDunks().add(waterDrunk);
    }

    public WaterDrunksForDay getTodayDate(User user) {
        LocalDate localDateNow = LocalDate.now();
        WaterDrunksForDay waterDrunksForDay;

        if (user.calendarWaterDrunk().isEmpty()) {
            return createNewDayDrunk(user, localDateNow);
        }
        waterDrunksForDay = user.calendarWaterDrunk().getLast();
        if (!waterDrunksForDay.date().equals(localDateNow)) {
            waterDrunksForDay = createNewDayDrunk(user, localDateNow);
        }

        return waterDrunksForDay;
    }

    private WaterDrunksForDay createNewDayDrunk(User user, LocalDate localDateNow) {
        List<WaterDrunk> waterDunks = new ArrayList<>();
        WaterDrunksForDay waterDrunksForDay = new WaterDrunksForDay(localDateNow, waterDunks);
        user.calendarWaterDrunk().add(waterDrunksForDay);
        return waterDrunksForDay;
    }

    public int countDrunkForDay(User user) {
        WaterDrunksForDay waterDrunksForDay = getTodayDate(user);
        int countWater = 0;
        for (WaterDrunk waterDrunk : waterDrunksForDay.waterDunks()) {
            countWater += waterDrunk.countWaterMl();
        }
        return countWater;
    }

    public String infoUser (){
        return "Пользователь" + user.name() + " Вес: " + user.weight();
    }

    public String fullInfoUser (){
        StringBuilder text = new StringBuilder("Пользователь" + user.name() + " Вес: " + user.weight() + "\n");
        for (WaterDrunksForDay day : user.calendarWaterDrunk()){
            text.append("День: ");
            text.append(day.date());
            text.append("\n");
            for (WaterDrunk drunk : day.waterDunks()){
                text.append("Выпил: ");
                text.append(drunk.countWaterMl());
                text.append("мл. Время: ");
                text.append(drunk.date());
                text.append("\n");
            }
        }
        return text.toString();
    }
}
