package kz.dar.internship.client.model;

import javax.validation.constraints.NotNull;

public class Client {
    private static long cnt;
    int id;
    @NotNull(message = "firstname can not be null")
    String firstName;
    String lastName;

    public Client(){}


}
