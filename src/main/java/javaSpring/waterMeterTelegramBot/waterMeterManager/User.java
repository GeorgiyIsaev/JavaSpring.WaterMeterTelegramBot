package javaSpring.waterMeterTelegramBot.waterMeterManager;


import java.util.ArrayList;
import java.util.Date;

import java.util.List;

public class User {
    private String name;
    private double weight;
    private List<WaterConsumptionCalendar> calendars;

    public User(String name, double weight) {
        this.name = name;
        this.weight = weight;
        calendars = new ArrayList<>();
    }

    public void addDrunkWater(int countDrunkWaterMl) {
        Date date = new Date();
        WaterConsumptionCalendar waterConsumptionCalendar = this.returnPresentDay(date);
        waterConsumptionCalendar.addData(date,countDrunkWaterMl);
    }

    public boolean isEqualsDay(WaterConsumptionCalendar waterConsumptionCalendar, Date date){
         if(waterConsumptionCalendar.getDate().getYear() == date.getYear() &&
                waterConsumptionCalendar.getDate().getMonth() == date.getMonth() &&
                waterConsumptionCalendar.getDate().getDay() == date.getDay()){
            return true;
        }
        return false;
    }

    public WaterConsumptionCalendar returnPresentDay(Date date){
        if(calendars.isEmpty()) {
            return null;
        }
        WaterConsumptionCalendar waterConsumptionCalendar = calendars.getLast();
        if(!this.isEqualsDay(waterConsumptionCalendar, date)){
            waterConsumptionCalendar = new WaterConsumptionCalendar(date);
        }
        return waterConsumptionCalendar;
    }



}
