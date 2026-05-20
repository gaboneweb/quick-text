package com.bulelani.cli.commands;

import com.bulelani.cli.Args;
import com.bulelani.shared.service.SnippetService;

public abstract class Command {

    public abstract void execute(SnippetService snippetService);

    public abstract void execute();

    public static Command create(Args args){
        switch (args.getCommand()){
            case "add":
                return new AddCommand(args.getParameter(0), args.getParameter(1));
            case "list":
                return new ListCommand();
            case "delete":
                return new DeleteCommand(args.getParameter(0));
            case "update":
                return new UpdateCommand(args.getParameter(0), args.getParameter(1));
            default:
                return new HelpCommand();
        }
    }
}
