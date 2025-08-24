package javaSpring.waterMeterTelegramBot.waterMeterManager.dataUser;

import java.util.Date;

public record WaterConsumption(Date date,
                               int countWaterMl) {
}
