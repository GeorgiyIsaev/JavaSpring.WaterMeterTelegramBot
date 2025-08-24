package javaSpring.waterMeterTelegramBot;

import javaSpring.waterMeterTelegramBot.consoleMenu.ConsoleManager;
import javaSpring.waterMeterTelegramBot.profiles.Profile;
import javaSpring.waterMeterTelegramBot.profiles.Profiles;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WaterMeterTelegramBotApplication {

	public static void main(String[] args) {
		SpringApplication.run(WaterMeterTelegramBotApplication.class, args);
	}


	@Bean
	public Profile createProfile(Profiles profiles){
		Profile profile = profiles.selectedProfile();
		System.out.println("Выбран Профиль: {"+ profile.name() + " - " + profile.key() + "}");
		return profile;
	}

	@Bean
	public ConsoleManager resetConsoleManager(ConsoleManager consoleManager){
		consoleManager.run();
		return consoleManager;
	}

}
