package com.bulelani.cli.commands;

import com.bulelani.shared.service.SnippetService;

public class HelpCommand extends Command{


    @Override
    public void execute(SnippetService snippetService) {
        execute();
    }

    @Override
    public void execute() {
        System.out.println("""
                
                 ██████╗ ██╗   ██╗██╗ ██████╗██╗  ██╗    ████████╗███████╗██╗  ██╗████████╗
                ██╔═══██╗██║   ██║██║██╔════╝██║ ██╔╝    ╚══██╔══╝██╔════╝╚██╗██╔╝╚══██╔══╝
                ██║   ██║██║   ██║██║██║     █████╔╝        ██║   █████╗   ╚███╔╝    ██║   
                ██║▄▄ ██║██║   ██║██║██║     ██╔═██╗        ██║   ██╔══╝   ██╔██╗    ██║   
                ╚██████╔╝╚██████╔╝██║╚██████╗██║  ██╗       ██║   ███████╗██╔╝ ██╗   ██║   
                 ╚══▀▀═╝  ╚═════╝ ╚═╝ ╚═════╝╚═╝  ╚═╝       ╚═╝   ╚══════╝╚═╝  ╚═╝   ╚═╝  
                                         CLI Snippet Expander
                """);

        System.out.println("""
                USAGE
                  java -jar quick-text-cli.jar <command> [arguments]
                
                COMMANDS
                  add <abbreviation> <expansion>      Add a new snippet
                  update <abbreviation> <expansion>   Update an existing snippet
                  delete <abbreviation>               Delete a snippet
                  list                                List all snippets
                  help                                Show this help message
                
                EXAMPLES
                  java -jar quick-text-cli.jar add :em "hello@example.com"
                  java -jar quick-text-cli.jar add :sig "Kind regards, Bulelani"
                  java -jar quick-text-cli.jar update :em "newemail@example.com"
                  java -jar quick-text-cli.jar delete :em
                  java -jar quick-text-cli.jar list
                
                NOTES
                  Wrap expansions containing spaces in double quotes.
                  Start the daemon separately to enable live text expansion:
                  java -jar quick-text-daemon.jar
                """);
        //System.out.println("You can only do the add command currently");
    }
}
