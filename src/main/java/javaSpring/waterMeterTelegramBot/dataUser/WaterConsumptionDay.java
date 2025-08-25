package javaSpring.waterMeterTelegramBot.dataUser;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@JsonAutoDetect
public class WaterConsumptionDay {
    private final Date date;

    private final List<WaterConsumption> consumes;

    public WaterConsumptionDay(Date date) {
        this.date = date;
        this.consumes = new ArrayList<>();
    }
    public WaterConsumptionDay(Date date, List<WaterConsumption> consumes) {
        this.date = date;
        this.consumes = consumes;
    }

    public Date getDate() {
        return date;
    }

    public List<WaterConsumption> getConsumes() {
        return consumes;
    }
    public void addData(Date data, int countDrunkWaterMl){
        consumes.add(new WaterConsumption(data,countDrunkWaterMl));
    }
    public int totalWaterDrunkForDay(){
        int sum = 0;
        for(WaterConsumption d : consumes)
            sum += d.countWaterMl();
        return sum;
    }
}
