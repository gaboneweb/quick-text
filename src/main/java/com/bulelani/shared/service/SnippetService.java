package com.bulelani.shared.service;

import com.bulelani.shared.model.Snippet;
import com.bulelani.shared.repository.ISnippetRepository;

import java.util.*;

public class SnippetService {
    private final ISnippetRepository repository;


    public SnippetService(ISnippetRepository repository) {
       this.repository = repository;
    }

    public void addSnippet(Snippet snippet){
        this.repository.save(snippet);
    }

    public void updateSnippet(Snippet snippet){
        this.repository.update(snippet);
    }

    public void deleteSnippet(String shortcut){
        this.repository.delete(shortcut);
    }

    public List<Snippet> getAll(){
        return this.repository.findAll();
    }

    public Optional<Snippet> search(String shortcut){
        return this.repository.findByShortcut(shortcut);
    }
}
