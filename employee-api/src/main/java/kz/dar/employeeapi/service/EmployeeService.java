package kz.dar.employeeapi.service;

import kz.dar.employeeapi.model.Department;
import kz.dar.employeeapi.model.EmployeeDTO;
import kz.dar.employeeapi.repository.entity.EmployeeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


public interface EmployeeService {

    EmployeeDTO createEmployee(EmployeeDTO employeeDTO);

    Iterable<EmployeeEntity> getEmployeeList();


    List<EmployeeDTO>getEmployeeListByDepartment(String department);

}
