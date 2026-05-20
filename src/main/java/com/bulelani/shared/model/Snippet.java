package com.bulelani.shared.model;

import java.time.LocalDateTime;

public class Snippet {
    private String expansion;
    private String abbreviation;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Snippet(String expansion, String abbreviation) {
        this.expansion = expansion;
        this.abbreviation = abbreviation;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
    }

    public Snippet(String abbreviation, String expansion, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.expansion = expansion;
        this.abbreviation = abbreviation;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getExpansion() {
        return expansion;
    }

    public void setExpansion(String expansion) {
        this.expansion = expansion;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Snippet snippet = (Snippet) o;

        return abbreviation != null && abbreviation.equals(snippet.abbreviation);
    }

    @Override
    public String toString() {
        return "Snippet{" +
                "expansion='" + expansion + '\'' +
                ", shortcut='" + abbreviation + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

}
