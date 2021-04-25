package com.shikhov.music_analyzer;

import com.shikhov.music_analyzer.commands.*;

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

        if (isAuthenticated || command instanceof ExitCommand) {
            this.command = command;
        } else {
            this.command = new UnauthorizedCommand();
        }
    }

    public void execute() {
        command.run();
    }
}
