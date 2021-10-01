package kz.dar.employeeapi.service;

import kz.dar.employeeapi.model.Department;
import kz.dar.employeeapi.model.EmployeeDTO;
import kz.dar.employeeapi.repository.entity.EmployeeEntity;
import kz.dar.employeeapi.repository.entity.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private ModelMapper modelMapper;


    @Autowired
    EmployeeRepository employeeRepository;


    EmployeeServiceImpl(){
        this.modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        EmployeeEntity employeeEntity = modelMapper.map(employeeDTO,EmployeeEntity.class);
        EmployeeEntity entity = employeeRepository.save(employeeEntity);
        employeeDTO = modelMapper.map(entity,EmployeeDTO.class);
        return employeeDTO;
    }

    @Override
    public Iterable<EmployeeEntity> getEmployeeList() {
        return employeeRepository.findAll();
    }

    @Override
    public List<EmployeeDTO> getEmployeeListByDepartment(String department) {
        List<EmployeeDTO> employeeDTOList = employeeRepository.findByDepartment(department).stream().map(employee->modelMapper.map(employee,EmployeeDTO.class)).collect(Collectors.toList());
        return employeeDTOList;
    }
}
