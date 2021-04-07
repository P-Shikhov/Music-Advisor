package com.shikhov.music_analyzer.commands;

public class FeaturedCommand extends HttpCommand {
    @Override
    public void run(){
        System.out.println("---FEATURED---");
        System.out.println("""
                Mellow Morning
                Wake Up and Smell the Coffee
                Monday Motivation
                Songs to Sing in the Shower""");
    }
}
