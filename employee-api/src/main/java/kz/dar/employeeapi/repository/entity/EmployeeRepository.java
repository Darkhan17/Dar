package kz.dar.employeeapi.repository.entity;

import kz.dar.employeeapi.model.Department;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface EmployeeRepository extends ElasticsearchRepository<EmployeeEntity,String> {
    List<EmployeeEntity> findByDepartment(String department);
}
