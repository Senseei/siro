package com.internacao.siro.entities;

import java.util.Objects;

import com.internacao.siro.dto.documentation.DocumentationDTO;
import com.internacao.siro.dto.documentation.NewDocumentationDTO;
import com.internacao.siro.dto.documentation.UpdateDocumentationDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_documentation")
public class Documentation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "register_id")
    private Register register;
    private String doc;
    private boolean canceled;

    public Documentation() {}

    public Documentation(DocumentationDTO dto) {
        id = dto.getId();
        doc = dto.getDoc();
        canceled = dto.isCanceled();
    }

    public Documentation(NewDocumentationDTO body) {
        doc = body.getDoc();
    }

    public void update(UpdateDocumentationDTO body) {
        if (body.getDoc() != null)
            doc = body.getDoc();
    }

    public boolean isCanceled() {
        return canceled;
    }

    public void cancel() {
        canceled = true;
    }

    public Long getId() {
        return id;
    }

    public Register getRegister() {
        return register;
    }

    public void setRegister(Register register) {
        this.register = register;
    }

    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
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
        Documentation other = (Documentation) obj;
        return Objects.equals(id, other.id);
    }

}