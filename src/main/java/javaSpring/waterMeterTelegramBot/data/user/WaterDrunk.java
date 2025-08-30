package javaSpring.waterMeterTelegramBot.data.user;

import java.time.LocalDateTime;

public record WaterDrunk(LocalDateTime date,
                         int countWaterMl) {
}
