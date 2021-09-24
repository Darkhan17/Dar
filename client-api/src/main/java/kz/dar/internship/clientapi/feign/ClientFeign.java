package kz.dar.internship.clientapi.feign;

import kz.dar.internship.clientapi.model.PackageViewModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name = "post-core-api")
@RequestMapping("/post")
public interface ClientFeign {

    @GetMapping("/healthcheck")
    String healthCheck();

    @GetMapping("/client/{clientId}")
    List<PackageViewModel> getListPackageBySenderId(@PathVariable String clientId);

}
