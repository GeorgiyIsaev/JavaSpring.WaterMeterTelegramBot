package javaSpring.waterMeterTelegramBot.profles;


import javaSpring.waterMeterTelegramBot.utils.ConsoleController;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Path;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


@Component
public class Profiles {
    private final Map<String, Profile> profiles;
    ConsoleController console;
    String pathProfiles = "date" +  File.separator + "profiles";

    public Profiles(ConsoleController console){
        this.profiles = new HashMap<>();
        this.console = console;

        LoaderProfiles loaderProfiles = new LoaderProfiles();
        Path pathProfile = Path.of(pathProfiles);
        loaderProfiles.loadProfiles(pathProfile,  this.profiles);
    }

    public Map<String, Profile> getProfiles() {
        return Collections.unmodifiableMap(profiles);
    }

    public Profile createNewProfile(){
        String name = console.input("Введите имя профиля:").toLowerCase();
        String key = console.input("Введите ключ профиля:").toLowerCase();
        return this.addProfile(name,key);
    }

    public Profile createNewProfile(String name){
        String key = console.input("Введите ключ для профиля "+name+":").toLowerCase();
        return this.addProfile(name,key);
    }


    public Profile addProfile(String name, String key){
        name = deleteSpecialCharacters(name);
        Profile profile = new Profile(name,key);
        profiles.put(name, profile);
        saveProfile(profile);
        return profile;
    }

    public String deleteSpecialCharacters(String text){
        return text.replace("\\", "");
    }

    public void saveProfile(Profile profile){
        SaverProfiles saverProfiles = new SaverProfiles();
        Path pathProfile = Path.of( "date" +  File.separator + "profiles" + File.separator + profile.name()+".txt");
        saverProfiles.write(pathProfile, profile);
    }

    public Profile selectedProfile(){
        if(!profiles.isEmpty()) {
            System.out.println("Меню выбора профиля: ");
        }
        int count = 0;
        for(Profile profile : profiles.values()){
            System.out.println(count++ + ": {" + profile.name()+ " - " + profile.key() + "};");
        }
        String input = console.input("Введите имя существующего профиля или создайте новый введя новое имя: ").toLowerCase();
        Profile profile = getProfile(input);
        if(profile == null){
            profile = createNewProfile(input);
        }
        return profile;
    }


   public Profile getProfile(String input){
       return profiles.get(input.toLowerCase());
    }

}
