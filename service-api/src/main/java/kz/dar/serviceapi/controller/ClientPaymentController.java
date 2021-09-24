package kz.dar.serviceapi.controller;


import kz.dar.serviceapi.feign.ClientFeign;
import kz.dar.serviceapi.model.*;
import kz.dar.serviceapi.service.ClientPaymentService;
import kz.dar.serviceapi.service.ClientPaymentServiceImpl;
import org.apache.http.protocol.HTTP;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/service")
public class ClientPaymentController {

    @Autowired
    ClientPaymentServiceImpl clientPaymentService;

    @Autowired
    ClientFeign clientFeign;

    private ModelMapper modelMapper;

    ClientPaymentController(){
        this.modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }


    private ClientPaymentViewModel getViewModel(ClientPaymentResponse clientPaymentResponse){
        ClientPaymentViewModel clientPaymentViewModel = modelMapper.map(clientPaymentResponse, ClientPaymentViewModel.class);
        ClientResponseModel client = clientFeign.getClient(clientPaymentResponse.getClientId());
        clientPaymentViewModel.setClient(client);
        return clientPaymentViewModel;
    }



    @PostMapping()
    public ResponseEntity<ClientPaymentResponse> createServicePayment(@RequestBody ClientPaymentRequest clientPaymentRequest){

        ClientPaymentDTO clientPaymentDTO = modelMapper.map(clientPaymentRequest, ClientPaymentDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(clientPaymentService.createClientPayment(clientPaymentDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientPaymentViewModel> getClientPayment(@PathVariable String id) {
        ClientPaymentResponse clientPaymentResponse = clientPaymentService.getClientPaymentById(id);
        ClientPaymentViewModel clientPaymentViewModel = getViewModel(clientPaymentResponse);
        return ResponseEntity.status(HttpStatus.OK).body(clientPaymentViewModel);
    }


    @GetMapping("/all/{page}")
    public List<ClientPaymentViewModel> getPaymentList(@PathVariable int page){
        List<ClientPaymentViewModel> clientPayments = clientPaymentService.getClientPaymentList(page).stream().map(payment->getViewModel(payment)).collect(Collectors.toList());
        return clientPayments;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFromDatabase(@PathVariable String id){
        clientPaymentService.deleteClientPayment(id);
        return new ResponseEntity<>("Successfully deleted!",HttpStatus.OK);
    }


    @PutMapping("{id}")
    public ResponseEntity<ClientPaymentResponse> updateClientPayment(@PathVariable String id, @RequestBody ClientPaymentRequest clientPaymentRequest){
        ClientPaymentDTO clientPaymentDTO = modelMapper.map(clientPaymentRequest, ClientPaymentDTO.class);
        ClientPaymentResponse clientPaymentResponse = clientPaymentService.updateClientPayment(id,clientPaymentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(clientPaymentResponse);
    }


    // CLIENT-API
    @GetMapping("/clientPayment")
    public List<ClientPaymentResponse> getClientPaymentList(@RequestParam String clientId){
        List<ClientPaymentResponse> clientPaymentResponseList = clientPaymentService.getClientPaymentByClientId(clientId);
        return clientPaymentResponseList;
    }



}
