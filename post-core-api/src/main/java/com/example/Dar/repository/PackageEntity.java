package com.example.Dar.repository;

import com.example.Dar.model.Status;

import javax.persistence.*;

@Entity
@Table( name = "package")
public class PackageEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 50)
    private String senderId;

    @Column(nullable = false, length = 50)
    private String recipientId;

    @Column(nullable = false, length = 50)
    private String description;

    @Column(nullable = false, length = 50)
    private Status status;


    public Long getId() {
        return id;
    }



    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String sender) {
        this.senderId = sender;
    }

    public String getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(String recipient) {
        this.recipientId = recipient;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
