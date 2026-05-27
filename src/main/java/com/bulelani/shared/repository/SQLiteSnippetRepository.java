package com.bulelani.shared.repository;

import com.bulelani.shared.model.Snippet;
import org.sqlite.SQLiteConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SQLiteSnippetRepository implements ISnippetRepository {

    private Connection connection;

    public SQLiteSnippetRepository(String dbPath,boolean readOnly) {
        try {
            String url = "jdbc:sqlite:file:" + dbPath ;
            SQLiteConfig config = new SQLiteConfig();
            config.setReadOnly(readOnly);
            if (readOnly){
                this.connection = DriverManager.getConnection(url+ "?mode=ro", config.toProperties());
            }else{
                this.connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
            }

            initSchema();
        } catch (SQLException e) {
            throw new RuntimeException("Could not initialise database at: " + dbPath, e);
        }
    }

    private void initSchema() {
        String sql = """
            CREATE TABLE IF NOT EXISTS snippets (
                id          INTEGER PRIMARY KEY AUTOINCREMENT,
                abbreviation    TEXT    NOT NULL UNIQUE,
                expansion   TEXT    NOT NULL,
                created_at  TEXT    NOT NULL DEFAULT (datetime('now')),
                updated_at  TEXT    NOT NULL DEFAULT (datetime('now'))
            )
        """;
        try {
            connection.createStatement().execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Could not create database schema.", e);
        }
    }

    @Override
    public void save(Snippet snippet) {
        String sql = "INSERT INTO snippets (abbreviation, expansion) VALUES (?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, snippet.getAbbreviation());
            stmt.setString(2, snippet.getExpansion());
            stmt.executeUpdate();
        } catch (SQLException e) {
            // Most likely cause: duplicate shortcut violating UNIQUE constraint
            if (e.getMessage().contains("UNIQUE constraint failed")) {
                System.err.println("[ERROR] Abbreviation '" + snippet.getAbbreviation() + "' already exists.");
            } else {
                e.printStackTrace();
                System.err.println("[ERROR] Could not save snippet. Please try again.");
            }
        }
    }

    @Override
    public Optional<Snippet> findByShortcut(String shortcut) {
        String sql = "SELECT * FROM snippets WHERE abbreviation = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, shortcut);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(mapRow(rs));
            }
        } catch (SQLException e) {
            System.err.println("[ERROR] Could not search for snippet.");
        }
        return Optional.empty();
    }

    @Override
    public List<Snippet> findAll() {
        List<Snippet> snippets = new ArrayList<>();
        try {
            ResultSet rs = connection.createStatement()
                    .executeQuery("SELECT * FROM snippets");
            while (rs.next()) {
                snippets.add(mapRow(rs));
            }
        } catch (SQLException e) {
            System.err.println("[ERROR] Could not retrieve snippets.");
        }
        return snippets;
    }

    @Override
    public void delete(String abbreviation) {
        String sql = "DELETE FROM snippets WHERE abbreviation = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, abbreviation);
            int rows = stmt.executeUpdate();
            if (rows == 0) {
                // executeUpdate succeeds even if nothing matched — check rows affected
                System.err.println("[ERROR] Abbreviation '" + abbreviation + "' not found.");
            }
        } catch (SQLException e) {
            System.err.println("[ERROR] Could not delete snippet.");
        }
    }

    @Override
    public void update(Snippet snippet) {
        String sql = """
                        UPDATE snippets
                        SET  expansion = ?, updated_at = datetime('now')
                        WHERE abbreviation = ?
                     """;
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, snippet.getExpansion());
            stmt.setString(1, snippet.getAbbreviation());
            int rows = stmt.executeUpdate();
            if (rows == 0) {
                // executeUpdate succeeds even if nothing matched — check rows affected
                System.err.println("[ERROR] Abbreviation '" + snippet.getAbbreviation() + "' not updated.");
            }
        } catch (SQLException e) {
            System.err.println("[ERROR] Could not update snippet.");
        }
    }

    private Snippet mapRow(ResultSet rs) throws SQLException {
        return new Snippet(
                rs.getString("expansion"),
                rs.getString("abbreviation")
        );
    }
}