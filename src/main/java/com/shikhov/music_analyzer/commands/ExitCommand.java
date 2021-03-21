package com.shikhov.music_analyzer.commands;

public class ExitCommand implements Command {
    @Override
    public void run(){
        System.out.println("---GOODBYE!---");
    }
}
