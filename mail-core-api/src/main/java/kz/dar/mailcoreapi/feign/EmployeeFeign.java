package kz.dar.mailcoreapi.feign;


import kz.dar.mailcoreapi.model.EmployeeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@FeignClient(name = "employee-api")
@RequestMapping("/employee")
public interface EmployeeFeign {
    @GetMapping("/department")
    ResponseEntity<List<EmployeeDTO>> getEmployeeByDepartment(@RequestParam String department);


    @GetMapping("/check")
    public String check();
}
