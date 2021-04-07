package com.shikhov.music_analyzer.commands;

public class CategoriesCommand extends HttpCommand {
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