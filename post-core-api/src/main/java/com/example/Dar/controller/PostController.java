package com.example.Dar.controller;


import com.example.Dar.database.Database;
import com.example.Dar.feign.PostFeign;
import com.example.Dar.model.*;
import com.example.Dar.service.PackageServiceImpl;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/post")
public class PostController {

    Database database  = Database.getInstance();




    @Autowired
    Environment environment;

    @Autowired
    PackageServiceImpl packageService;

    @Autowired
    PostFeign postFeign;

    private ModelMapper modelMapper;

    PostController(){
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

    }

    private PackageViewModel getViewModel(PackageResponse packageResponse){
        String senderName = postFeign.getClient(packageResponse.getSenderId()).getName();
        String recipientName = postFeign.getClient(packageResponse.getRecipientId()).getName();
        PackageViewModel packageViewModel = modelMapper.map(packageResponse, PackageViewModel.class);
        packageViewModel.setSenderName(senderName);
        packageViewModel.setRecipientName(recipientName);
        return packageViewModel;
    }
    @GetMapping("/healthcheck")
    public String healthCheck(){

        return "it's working on port: " + environment.getProperty("local.server.port") ;
    }

    @GetMapping("/client/{clientId}")
    public List<PackageViewModel> getListPackageBySenderId(@PathVariable String clientId){

        List<PackageViewModel> packageList = packageService.getPackageListBySender(clientId).stream().map(pack->getViewModel(pack)).collect(Collectors.toList());

        return packageList;
    }


    @GetMapping("/all")
    public List<PackageResponse> getAllPackage(){
        List<PackageResponse> packageList = packageService.getAllPackage();
        return packageList;
    }


    @GetMapping("/{id}")
    public PackageViewModel getPackageById(@PathVariable String id){
        PackageResponse packageResponse = packageService.getPackage(id);
        return getViewModel(packageResponse);
    }


    @PostMapping()
    public ResponseEntity<PackageResponse> createPackage(@Valid @RequestBody PackageModelRequest packageDetail){

        packageDetail.setStatus(Status.IN_PROGRESS);
        PackageDTO packageDTO = modelMapper.map(packageDetail, PackageDTO.class);
        packageDTO.setPackageId(UUID.randomUUID().toString());

        PackageResponse packageResponse = packageService.createAndUpdatePackage(packageDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(packageResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PackageModelRequest> updatePackage(@Valid @RequestBody PackageModelRequest pack, @PathVariable long id){
        PackageModelRequest mail = database.getPackage(id);
        mail.setDescription(pack.getDescription());
        return new ResponseEntity<PackageModelRequest>(mail,HttpStatus.OK);
    }


    @PutMapping("/{id}/complete")
    ResponseEntity<?> complete(@PathVariable Long id) {

        PackageModelRequest order = database.getPackage(id);

        if (order != null && order.getStatus() == Status.IN_PROGRESS) {
            order.setStatus(Status.COMPLETED);
            return new ResponseEntity<PackageModelRequest>(order,HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
    }

}
