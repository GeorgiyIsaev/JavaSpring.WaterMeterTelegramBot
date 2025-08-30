package javaSpring.waterMeterTelegramBot.data.user;

import org.springframework.stereotype.Component;

import java.util.List;


public record User(String name,
                   int weight,
                   List<WaterDrunksForDay> calendarWaterDrunk){
    public String infoUser (User user){
        return "Пользователь" + user.name() + " Вес: " + user.weight();
    }

    public String fullInfoUser (User user){
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
