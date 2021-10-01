package kz.dar.employeeapi.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;



@Getter
@Setter
public class EmployeeResponse {

    private String id;

    private String name;

    private String email;

    private String phoneNumber;

    private double salary;

    private Department department;

}
