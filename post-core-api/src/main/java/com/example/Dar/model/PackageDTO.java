package com.example.Dar.model;

public class PackageDTO{


    private String packageId;
    private String senderId;
    private String recipientId;
    private String description;
    private Status status;

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(String recipientId) {
        this.recipientId = recipientId;
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

    @Override
    public String toString() {
        return "PackageDTO{" + "id=" + this.packageId + ", description='" + this.description + '\'' + ", status=" + this.status + "/n"+
                "sender: " + getSenderId() + " recipient: " + getRecipientId() + '}';
    }
}
