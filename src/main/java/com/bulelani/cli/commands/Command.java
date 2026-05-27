package com.bulelani.cli.commands;

import com.bulelani.cli.Args;
import com.bulelani.shared.service.SnippetService;

public abstract class Command {

    public abstract void execute(SnippetService snippetService);

    public static Command create(Args args){
        return switch (args.getCommand()) {
            case "add" -> new AddCommand(args.getParameter(0), args.getParameter(1));
            case "list" -> new ListCommand();
            case "delete" -> new DeleteCommand(args.getParameter(0));
            case "update" -> new UpdateCommand(args.getParameter(0), args.getParameter(1));
            default -> new HelpCommand();
        };
    }
}
