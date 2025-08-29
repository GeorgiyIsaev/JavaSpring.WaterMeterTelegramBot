package javaSpring.waterMeterTelegramBot;
import javaSpring.waterMeterTelegramBot.data.user.User;
import javaSpring.waterMeterTelegramBot.data.user.WaterDrunksForDay;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
public class WaterMeterTelegramBotApplication {

	public static void main(String[] args) {
		SpringApplication.run(WaterMeterTelegramBotApplication.class, args);
	}

//	@Bean
//	public SaveFileUser createSaveFileUser(){
//		return new SaveFileUser(Path.of("data"));
//	}
//	@Bean
//	public Profile createProfile(Profiles profiles){
//		Profile profile = profiles.selectedProfile();
//		System.out.println("Выбран Профиль: {"+ profile.name() + " - " + profile.key() + "}");
//		return profile;
//	}

//	@Bean
//	public ConsoleManager resetConsoleManager(ConsoleManager consoleManager){
//		consoleManager.run();
//		return consoleManager;
//	}

	@Bean
		public User resetConsoleManager(){
		System.out.println("Создаем");
		String name = "Виктор";

		int weight = 75;
		List<WaterDrunksForDay> calendarWaterDrunk = new ArrayList<>();
		User user = new User(name, weight, calendarWaterDrunk);
		System.out.println("Объект создан");
		System.out.println(user.name() + " Вес" + user.weight());
		System.out.println(user.calendarWaterDrunk().getLast().date());

		return user;
	}

}
