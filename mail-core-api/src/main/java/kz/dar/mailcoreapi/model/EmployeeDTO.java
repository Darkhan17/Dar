package kz.dar.mailcoreapi.model;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class EmployeeDTO implements Dispatched{

    private String id;

    private String name;

    private String email;

    private String phoneNumber;

    private double salary;


}
