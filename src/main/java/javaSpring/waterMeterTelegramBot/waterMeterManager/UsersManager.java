//package javaSpring.waterMeterTelegramBot.waterMeterManager;
//
//
//import javaSpring.waterMeterTelegramBot.data.profile.Profile;
//
//import java.util.HashMap;
//import java.util.Map;
//
//
//
//public class UsersManager implements IUsersManager{
//    private final Profile profile;
//    Map<String, User> users;
//
//    public UsersManager(Profile profile) {
//        this.profile = profile;
//        // System.out.println("Конструктор WaterMeterManager сообщает - Выбран Профиль: {"+ profile.name() + " - " + profile.key() + "}");
//        users = new HashMap<>();
//    }
//
//    public Profile getProfile() {
//        return profile;
//    }
//
//    ///  Создание или вызов Пользователя
//    @Override
//    public User getUserOrCreateIfNot(String nameUser){
//        User user = getUser(nameUser);
//        if(user == null){
//            return createUser(nameUser);
//        }
//        return user;
//    }
//    @Override
//    public User getUser(String nameUser){
//        return users.get(nameUser);
//    }
//    @Override
//    public User createUser(String nameUser, int weight){
//        User user = new User(nameUser, weight);
//        users.put(nameUser, user);
//        return user;
//    }
//    @Override
//    public User createUser(String nameUser){
//        User user = new User(nameUser, 0);
//        users.put(nameUser, user);
//        return user;
//    }
//
//    ///  Изменение веса пользователя
//    @Override
//    public User setWeightToUser(String nameUser, int weight){
//        User user = getUser(nameUser);
//        if(user != null){
//            user.setWeight(weight);
//        }
//        return user;
//    }
//
//    /// Запись информации о выпитой воде
//    @Override
//    public User waterDrunkUser(String nameUser, int countDrunkWaterMl){
//        User user = getUserOrCreateIfNot(nameUser);
//        user.addDrunkWater(countDrunkWaterMl);
//        return user;
//    }
//
//
//}
