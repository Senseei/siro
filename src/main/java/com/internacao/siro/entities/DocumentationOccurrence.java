package com.internacao.siro.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_documentation_occurrences")
public class DocumentationOccurrence {
    
    @EmbeddedId
    private DocumentationOccurrenceId id = new DocumentationOccurrenceId();
    private LocalDateTime date;

    public DocumentationOccurrence() {}

    public DocumentationOccurrence(Documentation documentation, Occurrence occurrence) {
        id.setDocumentation(documentation);
        id.setOccurrence(occurrence);
        date = LocalDateTime.now();
    }

    public Documentation getDocumentation() {
        return id.getDocumentation();
    }

    public void setDocumentation(Documentation documentation) {
        id.setDocumentation(documentation);
    }

    public Occurrence getOccurrence() {
        return id.getOccurrence();
    }

    public void setOccurrence(Occurrence occurrence) {
        id.setOccurrence(occurrence);
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DocumentationOccurrence other = (DocumentationOccurrence) obj;
        return Objects.equals(id, other.id);
    }
}
