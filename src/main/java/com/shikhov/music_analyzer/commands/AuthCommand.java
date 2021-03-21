package com.shikhov.music_analyzer.commands;

import com.shikhov.music_analyzer.commands.Command;

public class AuthCommand implements Command {
    @Override
    public void run(){
        System.out.println("https://accounts.spotify.com/authorize?client_id=a19ee7dbfda443b2a8150c9101bfd645&redirect_uri=http://localhost:8080&response_type=code");
    }
}
