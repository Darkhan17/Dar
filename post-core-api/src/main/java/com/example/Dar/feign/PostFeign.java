package com.example.Dar.feign;

import com.example.Dar.model.ClientResponseModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@FeignClient(name = "client-api")
@RequestMapping("client")
public interface PostFeign {
    @GetMapping("/{id}")
    ClientResponseModel getClient(@PathVariable String id);

}
