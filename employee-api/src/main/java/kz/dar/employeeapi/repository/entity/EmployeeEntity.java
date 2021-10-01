package kz.dar.employeeapi.repository.entity;


import kz.dar.employeeapi.model.Department;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;


@Setter
@Getter
@Document(indexName = "employee-api", createIndex = true)
public class EmployeeEntity {

    @Id
    @Field(type = FieldType.Keyword)
    private String id;

    @Field(type = FieldType.Keyword)
    private String name;

    @Field(type = FieldType.Keyword)
    private String email;

    @Field(type = FieldType.Keyword)
    private String phoneNumber;

    @Field(type = FieldType.Keyword)
    private double salary;

    @Field(type = FieldType.Keyword)
    private Department department;


}
