package com.shikhov.music_analyzer.commands;

public class CategoriesCommand implements Command {
    @Override
    public void run(){
        System.out.println("---CATEGORIES---");
        System.out.println("""
                Top Lists
                Pop
                Mood
                Latin""");
    }
}