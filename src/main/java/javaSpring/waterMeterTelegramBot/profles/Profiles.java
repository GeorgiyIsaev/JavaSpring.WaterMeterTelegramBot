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

    public void createNewProfile(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("input name Profile: ");
        String name = scanner.nextLine();
        System.out.print("input key Profile: ");
        String key = scanner.nextLine();
        this.addProfile(name,key);
    }


    public void addProfile(String name, String key){
        Profile profile = new Profile(name,key);
        profiles.put(name, profile);
    }
}
