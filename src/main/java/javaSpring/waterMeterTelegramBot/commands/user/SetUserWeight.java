package javaSpring.waterMeterTelegramBot.commands.user;

import javaSpring.waterMeterTelegramBot.commands.base.ICommand;
import javaSpring.waterMeterTelegramBot.data.user.User;
import javaSpring.waterMeterTelegramBot.service.store.user.UsersStore;

public class SetUserWeight implements ICommand {
    private final String name;
    UsersStore usersStore;

    public SetUserWeight(String name, UsersStore usersStore) {
        this.name = name;
        this.usersStore = usersStore;
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public String description() {
        return "\"" + getName() + " 'новое значение веса'\" – Изменит вес пользователя";
    }

    @Override
    public String start(User user, String[] message) {
        if(message.length <3){
            return "Ошибка передачи данных при изменении веса!";
        }

        int weight;
        try {
            weight = Integer.parseInt(message[2]);
        } catch (NumberFormatException e) {
            return "Ошибка передачи данных при изменении веса!";
        }

        if(user == null){
            return "Пользователь не найден!";
        }

        User userChange = usersStore.setWeightToUser(user, weight);
        return "Вес " + userChange.name() + " составляет " + userChange.weight();
    }


}