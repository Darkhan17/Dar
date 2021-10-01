package kz.dar.serviceapi.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "mail-core-api")
@RequestMapping("mail")
public interface MailFeign {


    @GetMapping("/send")
    public String sendMessage();
}
