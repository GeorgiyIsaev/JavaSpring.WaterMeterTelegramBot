package javaSpring.waterMeterTelegramBot.dataUser;

import java.util.Date;

public record WaterConsumption(Date date,
                               int countWaterMl) {
}
