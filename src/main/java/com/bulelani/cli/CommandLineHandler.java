package com.bulelani.cli;


public class CommandLineHandler {

    Args commandlineArguments;

    public CommandLineHandler(String[] args){
        this.commandlineArguments = parseArgs(args);
    }

    private Args parseArgs(String[] args){
        return Args.parse(args);
    }


    public Args getCommandlineArguments() {
        return commandlineArguments;
    }
}
