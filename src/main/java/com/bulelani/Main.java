package com.bulelani;

import com.bulelani.cli.CommandLineHandler;
import com.bulelani.cli.commands.Command;
import com.bulelani.shared.AppConfig;
import com.bulelani.shared.model.Snippet;
import com.bulelani.shared.repository.InMemorySnippetRepository;
import com.bulelani.shared.repository.SQLiteSnippetRepository;
import com.bulelani.shared.service.SnippetService;

import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        AppConfig.init();
        CommandLineHandler handler = new CommandLineHandler(args);
        SnippetService service = new SnippetService(new SQLiteSnippetRepository(AppConfig.DB_PATH,false));

        Command command = Command.create(handler.getCommandlineArguments());
        command.execute(service);

    }


    private static List<Snippet> createTestSnippets() {

        LocalDateTime now = LocalDateTime.now();

        return List.of(

                new Snippet(
                        "brb",
                        "Be right back",
                        now,
                        now
                ),

                new Snippet(
                        "gm",
                        "Good morning",
                        now,
                        now
                ),

                new Snippet(
                        "sig",
                        """
                        Kind regards,
                        Bulelani Gabonewe
                        Software Developer
                        """,
                        now,
                        now
                )
        );
    }
}