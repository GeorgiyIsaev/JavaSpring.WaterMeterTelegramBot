package javaSpring.waterMeterTelegramBot.waterMeterManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WaterConsumptionCalendar {
    private List<Date> dates;

    public WaterConsumptionCalendar() {
        this.dates = new ArrayList<>();
    }

    public List<Date> getDates() {
        return dates;
    }
    public void addData(Date data, int water){

    }
}
