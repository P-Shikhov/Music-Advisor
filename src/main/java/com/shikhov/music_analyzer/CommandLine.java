package com.shikhov.music_analyzer;

import com.shikhov.music_analyzer.commands.AuthCommand;
import com.shikhov.music_analyzer.commands.Command;
import com.shikhov.music_analyzer.commands.HttpCommand;
import com.shikhov.music_analyzer.commands.UnauthorizedCommand;

public class CommandLine {

    private boolean isAuthenticated;
    private Command command;

    public CommandLine() {
        this.isAuthenticated = false;
    }

    public void setCommand(Command command) {
        if (command instanceof AuthCommand) {
            isAuthenticated = true;
        }

        if (isAuthenticated) {
            this.command = command;
        } else {
            this.command = new UnauthorizedCommand();
        }
    }

    public void execute() {
        command.run();
    }
}
