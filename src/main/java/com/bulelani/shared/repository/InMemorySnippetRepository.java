package com.bulelani.shared.repository;

import com.bulelani.shared.model.Snippet;

import java.util.*;

public class InMemorySnippetRepository implements ISnippetRepository{
    private final Map<String, Snippet> snippetMap;


    public InMemorySnippetRepository() {
        snippetMap = new LinkedHashMap<>();
    }

    public InMemorySnippetRepository(List<Snippet> snippetList){
        snippetMap = new LinkedHashMap<>();
        for(Snippet p: snippetList){
            this.save(p);
        }
    }
    @Override
    public void save(Snippet snippet) {
        this.snippetMap.put(snippet.getAbbreviation(), snippet);
    }

    @Override
    public void delete(String shortcut) {
        snippetMap.remove(shortcut);
    }

    /**
     * @param snippet
     */
    @Override
    public void update(Snippet snippet) {
        this.snippetMap.put(snippet.getAbbreviation(), snippet);
    }

    @Override
    public Optional<Snippet> findByShortcut(String shortcut) {
        return Optional.ofNullable(this.snippetMap.get(shortcut));
    }

    @Override
    public List<Snippet> findAll() {
        return new ArrayList<>(this.snippetMap.values());
    }
}
