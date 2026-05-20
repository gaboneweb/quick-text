package com.bulelani.shared.repository;

import com.bulelani.shared.model.Snippet;

import java.util.List;
import java.util.Optional;

public interface ISnippetRepository {
    void save(Snippet snippet);

    void delete(String shortcut);

    void update(Snippet snippet);

    Optional<Snippet> findByShortcut(String shortcut);

    List<Snippet> findAll();;
}
