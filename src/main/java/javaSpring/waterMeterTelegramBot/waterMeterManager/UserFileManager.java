//package javaSpring.waterMeterTelegramBot.waterMeterManager;
//
//import javaSpring.waterMeterTelegramBot.data.profile.Profile;
//import javaSpring.waterMeterTelegramBot.storingUserData.SaveFileUser;
//import org.springframework.stereotype.Component;
//
//@Component
//public class UserFileManager extends  UsersManager{
//    private final SaveFileUser saveFileUser;
//    public UserFileManager(Profile profile, SaveFileUser saveFileUser) {
//        super(profile);
//        System.out.println("ЗДЕСЬ");
//        this.saveFileUser = saveFileUser;
//    }
//
//    @Override
//    public User createUser(String nameUser) {
//        User user = super.createUser(nameUser);
//        saveFileUser.save(getProfile().name(), nameUser, user);
//        return user;
//    }
//
//    @Override
//    public User createUser(String nameUser, int weight) {
//        User user = super.createUser(nameUser, weight);
//        saveFileUser.save(getProfile().name(), nameUser, user);
//        return user;
//    }
//
//
//    @Override
//    public User setWeightToUser(String nameUser, int weight) {
//        User user = super.setWeightToUser(nameUser, weight);
//        saveFileUser.save(getProfile().name(), nameUser, user);
//        return user;
//
//    }
//
//    @Override
//    public User waterDrunkUser(String nameUser, int countDrunkWaterMl) {
//        User user = super.waterDrunkUser(nameUser, countDrunkWaterMl);
//        saveFileUser.save(getProfile().name(), nameUser, user);
//        return user;
//    }
//}
