package com.shikhov.music_analyzer;

import com.shikhov.music_analyzer.commands.*;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        CommandLine commandLine = new CommandLine();
        Scanner scanner = new Scanner(System.in);
        String currentCommand;
        do {
            currentCommand = scanner.nextLine();
            switch (currentCommand) {
                case "new" -> commandLine.setCommand(new NewCommand());
                case "featured" -> commandLine.setCommand(new FeaturedCommand());
                case "auth" -> {
                    System.out.println("auth");
                    commandLine.setCommand(new AuthCommand());
                }
                case "categories" -> commandLine.setCommand(new CategoriesCommand());
                case "playlists Mood" -> commandLine.setCommand(new PlaylistsCommand());
                case "exit" -> commandLine.setCommand(new ExitCommand());
                default -> throw new IllegalStateException("Unexpected value: " + currentCommand);
            }
            commandLine.execute();
        } while (!currentCommand.equals("exit"));
    }
}
