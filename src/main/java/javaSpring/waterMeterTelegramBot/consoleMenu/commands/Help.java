package javaSpring.waterMeterTelegramBot.consoleMenu.commands;


import javaSpring.waterMeterTelegramBot.consoleMenu.ConsoleManager;

public class Help extends CommandBase {

    public Help(String name, ConsoleManager consoleManager) {
        super(name, consoleManager);
    }

    @Override
    public String description(){
        return  "\"" + getName() + "\" – показать список команда";
    }


    @Override
    public String start(String message) {
        StringBuilder text = new StringBuilder("Команды\n");
        for(ICommand iCommand : getConsoleManager().getCommands().values()){
            text.append(iCommand.description());
            text.append("\n");
        }
        text.append("\n");
        return text.toString();
    }

}