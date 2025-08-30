package javaSpring.waterMeterTelegramBot.data.user;

import java.time.LocalDate;
import java.util.List;


public record WaterDrunksForDay(LocalDate date,
                                List<WaterDrunk> waterDunks) {
}
