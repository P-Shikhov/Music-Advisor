package com.shikhov.music_analyzer.commands;

public class UnauthorizedCommand implements Command {
    @Override
    public void run() {
        System.out.println("Please, provide access for application.");
    }
}
