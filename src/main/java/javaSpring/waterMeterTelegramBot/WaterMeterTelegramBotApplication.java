package javaSpring.waterMeterTelegramBot;
import javaSpring.waterMeterTelegramBot.data.profile.Profile;
import javaSpring.waterMeterTelegramBot.data.user.User;
import javaSpring.waterMeterTelegramBot.repository.user.LoadFromFileUser;
import javaSpring.waterMeterTelegramBot.service.contoller.UserController;
import javaSpring.waterMeterTelegramBot.service.store.UsersStore;
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

	@Bean
	public Path createSaveFileUser(){
		return Path.of("data");
	}
//	@Bean
//	public SaveFileUser createProfile(SaveFileUser save){
//		Path newPath = save.pathForUser("dwqd","dwewd");
//
//		System.out.println("newPath: "+ newPath);
//		return save;
//	}

//	@Bean
//	public ConsoleManager resetConsoleManager(ConsoleManager consoleManager){
//		consoleManager.run();
//		return consoleManager;
//	}



	@Bean
	public User resetConsoleManager(UsersStore usersStore, SaveFileUser save){
		User user = usersStore.getUserOrCreateIfNot("Витя");

		UserController usersManager = new UserController();
		System.out.println(	usersManager.infoUser(user));

		usersManager.drunkWater(user, 100);
		int countWater = usersManager.countDrunkForDay(user);
		System.out.println("Всего воды выпито за день: " + countWater);

		usersManager.drunkWater(user, 250);
		int countWater2 = usersManager.countDrunkForDay(user);
		System.out.println("Всего воды выпито за день: " + countWater2);


		System.out.println("Фул инфо: " + usersManager.fullInfoUser(user));

		Profile profile = new Profile("Тестовое имя", "Ключ");
		save.save(profile,user);

		LoadFromFileUser load = new LoadFromFileUser(Path.of("data"), profile);
		User userLod = load.load("Витя");
		if(userLod == null){
			System.out.println("Юзер не найден");
		}
		else {
			System.out.println("Фул инфо userLod: " + usersManager.fullInfoUser(userLod));
		}



		return user;

	}
}
