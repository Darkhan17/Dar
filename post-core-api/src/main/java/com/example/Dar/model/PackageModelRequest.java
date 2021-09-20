package com.example.Dar.model;



import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.NotNull;



public class PackageModelRequest {
    private  String packageId;
    @NotNull(message = "sender cannot be null")
    private String senderId;

    @NotNull(message = "recipient cannot be null")
    private String recipientId;

    @NotNull(message = "description cannot be null")
    private String description;

    private Status status;

    //public PackageModelRequest(){}

    public PackageModelRequest(String sender, String recipient, String description) {
        this.packageId = UUID.randomUUID().toString();
        this.recipientId = recipient;
        this.senderId = sender;
        this.description = description;
    }

    public String getId() {
        return packageId;
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
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof PackageModelRequest))
            return false;
        PackageModelRequest order = (PackageModelRequest) o;
        return Objects.equals(this.packageId, order.packageId) && Objects.equals(this.description, order.description)
                && this.status == order.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.packageId, this.description, this.status);
    }

    @Override
    public String toString() {
        return "Package{" + "id=" + this.packageId + ", description='" + this.description + '\'' + ", status=" + this.status + "/n"+
                "sender: " + getSenderId() + " recipient: " + getRecipientId() + '}';
    }

}
