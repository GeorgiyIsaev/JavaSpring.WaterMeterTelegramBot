package javaSpring.waterMeterTelegramBot.waterMeterManager;

import java.util.Date;

public record WaterConsumption(Date date,
                               int countWaterMl) {
}
