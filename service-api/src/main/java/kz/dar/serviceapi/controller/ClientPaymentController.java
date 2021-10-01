package kz.dar.serviceapi.controller;


import kz.dar.serviceapi.config.MessaginConfig;
import kz.dar.serviceapi.feign.ClientFeign;
import kz.dar.serviceapi.mail.Sender;
import kz.dar.serviceapi.model.*;
import kz.dar.serviceapi.service.ClientPaymentServiceImpl;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Validated
@RestController
@RequestMapping("/service")
public class ClientPaymentController {

    @Autowired
    ClientPaymentServiceImpl clientPaymentService;

    @Autowired
    ClientFeign clientFeign;

    @Autowired
    private RabbitTemplate template;

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

    private void sendToEmail(ClientPaymentViewModel clientPaymentViewModel){
        template.convertAndSend(MessaginConfig.EXCHANGE, MessaginConfig.ROUTING_KEY, clientPaymentViewModel);
    }


    @GetMapping("/check")
    public String check() throws Exception {
        template.convertAndSend(MessaginConfig.EXCHANGE, MessaginConfig.ROUTING_KEY, "hello");
        return "Success !!";
    }


    @PostMapping()
    public ResponseEntity<ClientPaymentResponse> createServicePayment(@Valid @RequestBody ClientPaymentRequest clientPaymentRequest){
        ClientPaymentDTO clientPaymentDTO = modelMapper.map(clientPaymentRequest, ClientPaymentDTO.class);
        ClientPaymentResponse clientPaymentResponse = clientPaymentService.createClientPayment(clientPaymentDTO);
        sendToEmail(getViewModel(clientPaymentResponse));
        return ResponseEntity.status(HttpStatus.CREATED).body(clientPaymentResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientPaymentViewModel> getClientPayment(@PathVariable String id) {
        ClientPaymentResponse clientPaymentResponse = clientPaymentService.getClientPaymentById(id);
        ClientPaymentViewModel clientPaymentViewModel = getViewModel(clientPaymentResponse);
        return ResponseEntity.status(HttpStatus.OK).body(clientPaymentViewModel);
    }


    @GetMapping("/all/{page}")
    public Page<ClientPaymentResponse> getPaymentPage(@PathVariable int page){
        /*Page<ClientPaymentViewModel> clientPayments = clientPaymentService.getClientPaymentList(page-1).map(payment->getViewModel(payment));*/
        Page<ClientPaymentResponse> clientPayments = clientPaymentService.getClientPaymentList(page-1);
        return clientPayments;
    }

    @GetMapping("/all/list/{page}")
    public ResponseEntity<ResponseList> getPaymentList(@PathVariable int page){
        Page<ClientPaymentViewModel> clientPayments = clientPaymentService.getClientPaymentList(page-1).map(payment->getViewModel(payment));
        List<ClientPaymentViewModel> clientPaymentResponses = clientPayments.stream().collect(Collectors.toList());
        ResponseList responseList = new ResponseList(clientPayments);
        return ResponseEntity.status(HttpStatus.OK).body(responseList);
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

    @DeleteMapping("delete")
    public void deleteAllData(){
        deleteAllData();
    }



}
