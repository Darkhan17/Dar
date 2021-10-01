package kz.dar.internship.clientapi.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class ClientRequestModel {
    private String id;
    @NotNull(message = "Name cannot be null")
    private String name;
    @Email
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
