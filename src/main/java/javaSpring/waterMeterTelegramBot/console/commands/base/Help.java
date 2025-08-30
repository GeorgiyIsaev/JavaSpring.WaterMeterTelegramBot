package javaSpring.waterMeterTelegramBot.console.commands.base;


import javaSpring.waterMeterTelegramBot.data.user.User;

import java.util.Map;

public class Help implements ICommand {

    private final String name;
    private final Map<String, ICommand> commands;

    public Help(String name, Map<String, ICommand> commands) {
        this.name = name;
        this.commands = commands;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String description(){
        return  "\"" + getName() + "\" – показать список команда";
    }

    @Override
    public String start(User user, String[] message) {
        StringBuilder text = new StringBuilder("Доступные команды:\n");
        text.append("Пожалуйста используйте следующий шаблон команд:\n" );
        text.append("'Команда' 'Имя пользователя' 'Сообщение'\n" );
        text.append("Exit - Выход из программы\n");
        for(ICommand iCommand : commands.values()){
            text.append(iCommand.description());
            text.append("\n");
        }
        text.append("\n");

        return text.toString();
    }
}