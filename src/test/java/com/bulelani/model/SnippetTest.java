package com.bulelani.model;

import com.bulelani.shared.model.Snippet;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SnippetTest {

    @Test
    public void TestCreate(){
        LocalDateTime now = LocalDateTime.now();
        Snippet snippet =   new Snippet(
                "brb",
                "Be right back",
                now,
                now
        );

        assertEquals("brb", snippet.getAbbreviation());
        assertEquals("Be right back", snippet.getExpansion());
        assertEquals(now, snippet.getCreatedAt());
        assertEquals(now, snippet.getUpdatedAt());
    }
}
