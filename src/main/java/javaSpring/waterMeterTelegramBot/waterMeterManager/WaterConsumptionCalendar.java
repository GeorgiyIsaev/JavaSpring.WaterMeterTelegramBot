package javaSpring.waterMeterTelegramBot.waterMeterManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WaterConsumptionCalendar {
    private final List<WaterConsumption> consumes;

    public WaterConsumptionCalendar() {
        this.consumes = new ArrayList<>();
    }

    public List<WaterConsumption> getConsumes() {
        return consumes;
    }
    public void addData(Date data, int water){
        consumes.add(new WaterConsumption(data,water));
    }
    public int totalWaterDrunkForDay(){
        int sum = 0;
        for(WaterConsumption d : consumes)
            sum += d.countWaterMl();
        return sum;
    }
}
