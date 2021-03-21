package com.shikhov.music_analyzer.commands;

public class PlaylistsCommand implements Command {
    @Override
    public void run(){
        System.out.println("---MOOD PLAYLISTS---");
        System.out.println("""
                Walk Like A Badass \s
                Rage Beats \s
                Arab Mood Booster \s
                Sunday Stroll""");
    }
}
