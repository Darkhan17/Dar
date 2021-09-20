package kz.dar.internship.clientapi.model;

import javax.validation.constraints.NotNull;

public class ClientRequestModel {
    private String id;
    @NotNull(message = "Name cannot be null")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
