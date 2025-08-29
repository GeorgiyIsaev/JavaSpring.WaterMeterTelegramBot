package javaSpring.waterMeterTelegramBot.data.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;


public record WaterDrunksForDay(LocalDate date,
                                List<WaterDrunk> waterDunks) {
}
