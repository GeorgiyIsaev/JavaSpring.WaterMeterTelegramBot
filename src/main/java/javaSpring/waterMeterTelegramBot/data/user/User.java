package javaSpring.waterMeterTelegramBot.data.user;

import org.springframework.stereotype.Component;

import java.util.List;


public record User(String name,
                   int weight,
                   List<WaterDrunksForDay> calendarWaterDrunk){
}
