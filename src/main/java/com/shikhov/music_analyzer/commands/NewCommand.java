package com.shikhov.music_analyzer.commands;

import com.shikhov.music_analyzer.commands.Command;

public class NewCommand implements Command {
    @Override
    public void run(){
        System.out.println("---NEW RELEASES---");
        System.out.println("""
                Mountains [Sia, Diplo, Labrinth]
                Runaway [Lil Peep]
                The Greatest Show [Panic! At The Disco]
                All Out Life [Slipknot]""");
    }
}
