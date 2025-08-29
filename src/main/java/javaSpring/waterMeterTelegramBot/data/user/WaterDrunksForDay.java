package javaSpring.waterMeterTelegramBot.data.user;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;


public record WaterDrunksForDay(LocalDateTime date,
                                List<WaterDrunk> waterDunks) {
}
