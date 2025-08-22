package javaSpring.waterMeterTelegramBot.profles;


import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


@Component
public class Profiles {
    private final Map<String, Profile> profiles;

    public Profiles(){
        this.profiles = new HashMap<>();

    }

    public Map<String, Profile> getProfiles() {
        return Collections.unmodifiableMap(profiles);
    }

    public Profile createNewProfile(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("input name Profile: ");
        String name = scanner.nextLine();
        System.out.print("input key Profile: ");
        String key = scanner.nextLine();
        Profile profile = this.addProfile(name,key);
        scanner.close();
        return profile;
    }


    public Profile addProfile(String name, String key){
        Profile profile = new Profile(name,key);
        profiles.put(name, profile);
        return profile;
    }
    public Profile selectedProfile(){



        System.out.println("Меню выбора профиля: ");
        int count = 0;
        for(Profile profile : profiles.values()){
            System.out.println(count++ + ": {" + profile.name()+ " - " + profile.key() + "};");
        }


       // System.out.print("Введите имя существующего профиля или создайте новый введя новое имя: ");
       // Scanner scanner = new Scanner(System.in);
       // String input = scanner.nextLine();
       // scanner.close();

        Profile profile = getProfile("input");
        if(profile == null){
            profile = createNewProfile();
        }
        return profile;
    }


   public Profile getProfile(String input){
       return profiles.get(input.toLowerCase());
    }

}
