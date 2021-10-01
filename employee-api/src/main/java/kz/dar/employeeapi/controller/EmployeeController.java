package kz.dar.employeeapi.controller;


import kz.dar.employeeapi.model.Department;
import kz.dar.employeeapi.model.EmployeeDTO;
import kz.dar.employeeapi.model.EmployeeRequest;
import kz.dar.employeeapi.model.EmployeeResponse;
import kz.dar.employeeapi.repository.entity.EmployeeEntity;
import kz.dar.employeeapi.service.EmployeeServiceImpl;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employee")
public class EmployeeController {


    @Autowired
    EmployeeServiceImpl employeeService;

    private ModelMapper modelMapper;

    EmployeeController(){
        this.modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    @GetMapping("/check")
    public String check(){
        return "OK";
    }


    @PostMapping("/register")
    public ResponseEntity<EmployeeResponse> createEmployee(@RequestBody EmployeeRequest employeeRequest){
        EmployeeDTO employeeDTO = modelMapper.map(employeeRequest,EmployeeDTO.class);
        employeeDTO = employeeService.createEmployee(employeeDTO);
        EmployeeResponse employeeResponse = modelMapper.map(employeeDTO, EmployeeResponse.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeResponse);
    }


    @GetMapping("/all")
    ResponseEntity<Iterable<EmployeeEntity>> getEmployeeList() {
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.getEmployeeList());
    }


    @GetMapping("/department")
    ResponseEntity<List<EmployeeDTO>> getEmployeeByDepartment(@RequestParam String department){
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.getEmployeeListByDepartment(department));
    }
}
