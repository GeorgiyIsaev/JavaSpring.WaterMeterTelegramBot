package javaSpring.waterMeterTelegramBot.dataUser;


import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import java.util.List;

@JsonAutoDetect
public class User {
    private String name;
    private int weight;
    private List<WaterConsumptionDay> calendars;


    public User(String name, int weight) {
        this.name = name;
        this.weight = weight;
        calendars = new ArrayList<>();
    }

    public User(String name, int weight, List<WaterConsumptionDay> calendars) {
        this.name = name;
        this.weight = weight;
        this.calendars = calendars;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public List<WaterConsumptionDay> getCalendars() {
        return Collections.unmodifiableList(calendars);
    }

    public void addDrunkWater(int countDrunkWaterMl) {
        Date date = new Date();
        WaterConsumptionDay waterConsumptionDay = this.returnPresentDay(date);
        if(waterConsumptionDay == null){
            waterConsumptionDay = new WaterConsumptionDay(date);
            calendars.add(waterConsumptionDay);
        }
        waterConsumptionDay.addData(date,countDrunkWaterMl);
    }

    public boolean isEqualsDay(WaterConsumptionDay waterConsumptionDay, Date date){
         if(waterConsumptionDay.getDate().getYear() == date.getYear() &&
                waterConsumptionDay.getDate().getMonth() == date.getMonth() &&
                waterConsumptionDay.getDate().getDay() == date.getDay()){
            return true;
        }
        return false;
    }

    public WaterConsumptionDay returnPresentDay(Date date){
        if(calendars.isEmpty()) {
            return null;
        }
        WaterConsumptionDay waterConsumptionDay = calendars.getLast();
        if(!this.isEqualsDay(waterConsumptionDay, date)){
            waterConsumptionDay = new WaterConsumptionDay(date);
        }
        return waterConsumptionDay;
    }


}
