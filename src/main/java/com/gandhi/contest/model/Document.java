package com.gandhi.contest.model;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Document {

    @Id
    @GeneratedValue
    private UUID id;

    @Lob
    private byte[] file;

    @OneToOne(mappedBy = "document")
    private Contestant contestant;

    public Contestant getContestant() {
        return contestant;
    }

    public void setContestant(Contestant contestant) {
        this.contestant = contestant;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

}
