package javaSpring.waterMeterTelegramBot;

import javaSpring.waterMeterTelegramBot.consoleMenu.ConsoleManager;
import javaSpring.waterMeterTelegramBot.profiles.Profile;
import javaSpring.waterMeterTelegramBot.profiles.Profiles;
import javaSpring.waterMeterTelegramBot.storingUserData.SaveFileUser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.nio.file.Path;

@SpringBootApplication
public class WaterMeterTelegramBotApplication {

	public static void main(String[] args) {
		SpringApplication.run(WaterMeterTelegramBotApplication.class, args);
	}

//	@Bean
//	public SaveFileUser createSaveFileUser(){
//		return new SaveFileUser(Path.of("data"));
//	}
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
