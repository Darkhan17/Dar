package kz.dar.serviceapi.service;


import kz.dar.serviceapi.model.ClientPaymentDTO;
import kz.dar.serviceapi.model.ClientPaymentResponse;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ClientPaymentService {

    ClientPaymentResponse createClientPayment(ClientPaymentDTO clientPaymentDTO);


    ClientPaymentResponse getClientPaymentById(String id);


    List<ClientPaymentResponse> getClientPaymentByClientId(String clientId);

    List<ClientPaymentResponse> getClientPaymentList(int page);

    void deleteClientPayment(String id);


    ClientPaymentResponse updateClientPayment(String id, ClientPaymentDTO clientPaymentDTO);
}
