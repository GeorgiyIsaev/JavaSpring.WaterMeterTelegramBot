package javaSpring.waterMeterTelegramBot;
import javaSpring.waterMeterTelegramBot.data.user.User;
import javaSpring.waterMeterTelegramBot.service.contoller.UserController;
import javaSpring.waterMeterTelegramBot.service.store.UsersStore;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


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
	public User resetConsoleManager(UsersStore usersStore){
		User user = usersStore.getUserOrCreateIfNot("Витя");

		UserController usersManager = new UserController();
		System.out.println(	usersManager.infoUser(user));

		usersManager.drunkWater(user, 100);
		int countWater = usersManager.countDrunkForDay(user);
		System.out.println("Всего воды выпито за день: " + countWater);

		usersManager.drunkWater(user, 250);
		int countWater2 = usersManager.countDrunkForDay(user);
		System.out.println("Всего воды выпито за день: " + countWater2);
		return user;
	}

}
