package com.shikhov.music_analyzer.commands;

public class UnauthorizedCommand extends HttpCommand {
    @Override
    public void run() {
        System.out.println("Please, provide access for application.");
    }
}
