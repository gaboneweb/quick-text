package com.bulelani.daemon;

import com.bulelani.Main;
import com.bulelani.shared.AppConfig;
import com.bulelani.shared.model.Snippet;
import com.bulelani.shared.repository.ISnippetRepository;
import com.bulelani.shared.repository.InMemorySnippetRepository;
import com.bulelani.shared.repository.SQLiteSnippetRepository;
import com.github.kwhat.jnativehook.GlobalScreen;

import java.time.LocalDateTime;
import java.util.List;

public class Daemon {

    public static void main(String[] args){
        AppConfig.init();
        ISnippetRepository repository = new SQLiteSnippetRepository(AppConfig.DB_PATH,true);
        try{
            ExpansionService expansionService = new ExpansionService(repository);
            GlobalScreen.registerNativeHook();
            GlobalScreen.addNativeKeyListener(new KeyBoardListener(expansionService));
        }catch (Exception e){
            e.printStackTrace();
        }

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
                ),

                new Snippet(
                        "email",
                        "bulelani.gabonewe@core.co.za",
                        now,
                        now
                )
        );
    }
}
