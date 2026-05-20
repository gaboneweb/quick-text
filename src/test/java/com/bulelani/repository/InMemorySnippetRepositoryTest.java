package com.bulelani.repository;

import com.bulelani.shared.model.Snippet;
import com.bulelani.shared.repository.InMemorySnippetRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class InMemorySnippetRepositoryTest {

    private InMemorySnippetRepository repository;

    private List<Snippet> createTestSnippets() {

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

    @Test
    public void TestAddSnippet() {
        this.repository = new InMemorySnippetRepository();
        LocalDateTime date = LocalDateTime.now();
        Snippet snippet = new Snippet("brb", "Be right back", date, date);

        repository.save(snippet);
        Optional<Snippet> addedSnippet = repository.findByShortcut("brb");

        assertTrue(addedSnippet.isPresent());
        assertEquals("brb",addedSnippet.get().getAbbreviation() );
        assertEquals("Be right back",addedSnippet.get().getExpansion() );
    }


    @Test
    public void TestRemoveSnippet() {
        this.repository = new InMemorySnippetRepository(createTestSnippets());

        this.repository.delete("brb");

        assertFalse(this.repository.findByShortcut("brb").isPresent());
    }

    @Test
    public void TestGetAllSnippets(){
        this.repository = new InMemorySnippetRepository(createTestSnippets());
        assertEquals(this.repository.findAll(), createTestSnippets());
    }
}
