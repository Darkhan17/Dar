package kz.dar.internship.client.controller;


import kz.dar.internship.client.feign.ClientFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("register")
public class ClientController {
    @Autowired
    ClientFeign employeeFeign ;


    @GetMapping("healthcheck")
    public String healthCheck(){
        return "i am working";
    }
    @GetMapping("check/openfeign")
    public String checkEmFeignClient(){
        return employeeFeign.healthCheck();
    }


    @PostMapping("/register")
    public String register(String name){
        return "working";
    }
}
