package kz.dar.employeeapi.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;


@Getter
@Setter
public class EmployeeRequest {

    private String id;

    @NotNull(message = "name can't be null")
    private String name;

    @Email(message = "email is incorrect")
    private String email;

    @NotNull(message = "phoneNumber can't be null")
    private String s;

    @NotNull(message = "salary can't be null")
    private double salary;

    @NotNull(message = "Department can't be null")
    private Department department;

}
