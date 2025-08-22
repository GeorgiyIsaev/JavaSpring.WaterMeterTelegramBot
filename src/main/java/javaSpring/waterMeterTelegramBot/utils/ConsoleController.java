package javaSpring.waterMeterTelegramBot.utils;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleController {
    Scanner scanner;
    public ConsoleController(){
        this.scanner = new Scanner(System.in);
    }

    public String input(String text){
        System.out.print(text);
        String name = scanner.nextLine();
        return name;
    }
    public String input(){
        return  input("input text");
    }
}
