package kz.dar.internship.client.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "post-core-api")
public interface ClientFeign {



    @GetMapping("/post/healthcheck")
    public String healthCheck();
}
