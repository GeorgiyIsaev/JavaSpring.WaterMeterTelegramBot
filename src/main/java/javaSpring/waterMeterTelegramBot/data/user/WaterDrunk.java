package javaSpring.waterMeterTelegramBot.data.user;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


public record WaterDrunk(LocalDateTime date,
                         int countWaterMl) {
}
