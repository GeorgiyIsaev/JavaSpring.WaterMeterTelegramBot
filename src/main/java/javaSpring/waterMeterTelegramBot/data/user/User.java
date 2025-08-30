package javaSpring.waterMeterTelegramBot.data.user;

import org.springframework.stereotype.Component;

import java.util.List;


public record User(String name,
                   int weight,
                   List<WaterDrunksForDay> calendarWaterDrunk){
    public String infoUser (){
        return "Пользователь" + name() + " Вес: " + weight();
    }

    public String fullInfoUser (){
        StringBuilder text = new StringBuilder("Пользователь" + name() + " Вес: " + weight() + "\n");
        for (WaterDrunksForDay day : calendarWaterDrunk()){
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
