package javaSpring.waterMeterTelegramBot.console.utils;

import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleController {
    private final Scanner scanner;
    public ConsoleController(){
        this.scanner = new Scanner(System.in);
    }

    public String input(String text){
        System.out.print(text);
        return scanner.nextLine();
    }
    public String input(){
        return  input("input text");
    }

    @PreDestroy
    public void doDestruct(){
        this.scanner.close();
    }
}
