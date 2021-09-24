package kz.dar.internship.clientapi.controller;


import kz.dar.internship.clientapi.feign.ClientFeign;
import kz.dar.internship.clientapi.feign.ClientPaymentFeign;
import kz.dar.internship.clientapi.model.*;
import kz.dar.internship.clientapi.service.ClientService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    ClientService clientService;

    @Autowired
    ClientFeign clientFeign;

    @Autowired
    ClientPaymentFeign clientPaymentFeign;

    private ModelMapper modelMapper;

    ClientController(){
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }


    @GetMapping("/healthcheck")
    public String healthCheck(){
        return clientFeign.healthCheck();
    }

    @GetMapping("/{id}")
    public ClientResponseModel getClient(@PathVariable String id){
        return clientService.getClient(id);
    }

    @PostMapping("/register")
    public ClientResponseModel createUser(@Valid @RequestBody ClientRequestModel clientRequestModel){
        ClientDTO packageDTO = modelMapper.map(clientRequestModel, ClientDTO.class);

        ClientResponseModel clientResponseModel = clientService.createAndUpdate(packageDTO);
        return clientResponseModel;

    }


    // POST-CORE-API

    @GetMapping("packages/{id}")
    public List<PackageViewModel> getPackageListBySenderId(@PathVariable String id){
        return clientFeign.getListPackageBySenderId(id);
    }


    //SERVICE-API

    @GetMapping("/payments")
    public List<ClientPaymentResponse> getClinetPaymentsListBySenderId(@RequestParam String clientId){
        return clientPaymentFeign.getClientPaymentList(clientId);
    }




}
