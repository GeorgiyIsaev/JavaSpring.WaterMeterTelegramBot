package javaSpring.waterMeterTelegramBot.dataUser;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.Date;

@JsonAutoDetect
public record WaterConsumption(Date date,
                               int countWaterMl) {
}
