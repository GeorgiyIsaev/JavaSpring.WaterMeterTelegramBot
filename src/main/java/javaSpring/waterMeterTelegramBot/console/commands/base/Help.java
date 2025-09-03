package javaSpring.waterMeterTelegramBot.console.commands.base;


import java.util.Map;

public class Help implements Command {

    private final String name;
    private final Map<String, Command> commands;

    public Help(String name, Map<String, Command> commands) {
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
    public String start(String message) {
        StringBuilder text = new StringBuilder("Доступные команды:\n");
        text.append("Пожалуйста используйте следующий шаблон команд:\n" );
        text.append("'Команда' 'Имя пользователя' 'Сообщение'\n" );
        text.append("Exit - Выход из программы\n");
        for(Command command : commands.values()){
            text.append(command.description());
            text.append("\n");
        }
        text.append("\n");

        return text.toString();
    }
}