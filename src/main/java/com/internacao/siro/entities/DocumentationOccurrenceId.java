package com.internacao.siro.entities;

import java.util.Objects;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class DocumentationOccurrenceId {
    
    @ManyToOne
    @JoinColumn(name = "documentation_id")
    private Documentation documentation;

    @ManyToOne
    @JoinColumn(name = "occurrence_id")
    private Occurrence occurrence;

    public DocumentationOccurrenceId() {}

    public DocumentationOccurrenceId(Documentation documentation, Occurrence occurrence) {
        this.documentation = documentation;
        this.occurrence = occurrence;
    }

    public Documentation getDocumentation() {
        return documentation;
    }

    public void setDocumentation(Documentation documentation) {
        this.documentation = documentation;
    }

    public Occurrence getOccurrence() {
        return occurrence;
    }

    public void setOccurrence(Occurrence occurrence) {
        this.occurrence = occurrence;
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentation, occurrence);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DocumentationOccurrenceId other = (DocumentationOccurrenceId) obj;
        return Objects.equals(documentation, other.documentation) && Objects.equals(occurrence, other.occurrence);
    }
}
