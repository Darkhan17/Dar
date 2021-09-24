package kz.dar.internship.clientapi.feign;


import kz.dar.internship.clientapi.model.ClientPaymentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "service-api")
@RequestMapping("/service")
public interface ClientPaymentFeign {
    @GetMapping("/clientPayment")
    public List<ClientPaymentResponse> getClientPaymentList(@RequestParam String clientId);

}
