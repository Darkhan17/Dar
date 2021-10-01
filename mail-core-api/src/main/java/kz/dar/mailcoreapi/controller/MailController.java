package kz.dar.mailcoreapi.controller;


import kz.dar.mailcoreapi.config.MessaginConfig;
import kz.dar.mailcoreapi.feign.EmployeeFeign;
import kz.dar.mailcoreapi.mail.EmailServiceImpl;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mail")
public class MailController {



    @Autowired
    EmailServiceImpl emailService;


    @Qualifier("kz.dar.mailcoreapi.feign.EmployeeFeign")
    @Autowired
    EmployeeFeign employeeFeign;

    @Autowired
    private RabbitTemplate template;

    @GetMapping("/send")
    public String sendMessage(){
        emailService.sendSimpleMessage("darhan.zhaparov@gmail.com","Hello","adsd");
        return "ok";
    }


    @GetMapping("/check")
    public String bookOrder() {
        //restaurantservice
        //payment service
        return employeeFeign.check();
    }
}
