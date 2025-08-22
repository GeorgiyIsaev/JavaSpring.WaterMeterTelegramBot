package javaSpring.waterMeterTelegramBot;

import javaSpring.waterMeterTelegramBot.profles.Profile;
import javaSpring.waterMeterTelegramBot.profles.Profiles;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WaterMeterTelegramBotApplication {

	public static void main(String[] args) {
		SpringApplication.run(WaterMeterTelegramBotApplication.class, args);
	}


	@Bean
	public Profiles createParkinHendler(Profiles profiles){
		Profile profile = profiles.selectedProfile();
		System.out.println(profile.name() + " " + profile.key());

		return profiles;
	}

}
