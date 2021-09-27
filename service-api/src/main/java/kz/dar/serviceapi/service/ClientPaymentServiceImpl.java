package kz.dar.serviceapi.service;


import kz.dar.serviceapi.exeptions.ClientPaymentNotFound;
import kz.dar.serviceapi.model.ClientPaymentDTO;
import kz.dar.serviceapi.model.ClientPaymentResponse;
import kz.dar.serviceapi.model.ClientResponseModel;
import kz.dar.serviceapi.model.ServicePayment;
import kz.dar.serviceapi.repository.entiry.ClientPaymentEntity;
import kz.dar.serviceapi.repository.entiry.ClientPaymentRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientPaymentServiceImpl implements ClientPaymentService{


    @Autowired
    ClientPaymentRepository clientPaymentRepository;

    private ModelMapper modelMapper;
    ClientPaymentServiceImpl(){
        this.modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    @Override
    public ClientPaymentResponse createClientPayment(ClientPaymentDTO clientPaymentDTO) {
        ClientPaymentEntity clientPaymentEntity = modelMapper.map(clientPaymentDTO, ClientPaymentEntity.class);
        double totalAmount = clientPaymentEntity.getServicePayments().stream().mapToDouble(ServicePayment::getAmount).sum();
        clientPaymentEntity.setTotalAmount(totalAmount);
        clientPaymentEntity = clientPaymentRepository.save(clientPaymentEntity);
        ClientPaymentResponse clientPaymentResponse = modelMapper.map(clientPaymentEntity, ClientPaymentResponse.class);
        return clientPaymentResponse;
    }

    @Override
    public ClientPaymentResponse getClientPaymentById(String id){
        ClientPaymentEntity clientPaymentEntity = clientPaymentRepository.findById(id)
                .orElseThrow(() -> new ClientPaymentNotFound(id));
        ClientPaymentResponse clientPayment= modelMapper.map(clientPaymentEntity, ClientPaymentResponse.class);
        return clientPayment;
    }

    @Override
    public List<ClientPaymentResponse> getClientPaymentByClientId(String clientId){
        List<ClientPaymentResponse> clientPaymentResponsesList= clientPaymentRepository.findByClientId(clientId).stream().map(
                pack-> modelMapper.map(pack, ClientPaymentResponse.class)).collect(Collectors.toList());
        return clientPaymentResponsesList;
    }

    @Override
    public Page<ClientPaymentResponse> getClientPaymentList(int page) {
        Pageable pageable = PageRequest.of(page,5);
        Page<ClientPaymentResponse> clientPayments = clientPaymentRepository.findAll(pageable).map(payment ->modelMapper.map(payment,ClientPaymentResponse.class));
        return clientPayments;
    }

    @Override
    public void deleteClientPayment(String id) {
        clientPaymentRepository.deleteById(id);
    }

    @Override
    public ClientPaymentResponse updateClientPayment(String id, ClientPaymentDTO clientPaymentDTO) {

        ClientPaymentEntity clientPaymentEntity = modelMapper.map(clientPaymentDTO, ClientPaymentEntity.class);
        return clientPaymentRepository.findById(id)
                .map(clientPayment -> {
                    clientPayment.setTotalAmount(clientPaymentEntity.getTotalAmount());
                    clientPayment.setClientId(clientPaymentEntity.getClientId());
                    clientPayment.setServicePayments(clientPaymentEntity.getServicePayments());
                    double totalAmount = clientPaymentEntity.getServicePayments().stream().mapToDouble(ServicePayment::getAmount).sum();
                    clientPayment.setTotalAmount(totalAmount);
                    clientPaymentRepository.save(clientPayment);
                    return modelMapper.map(clientPayment,ClientPaymentResponse.class);
                })
                .orElseGet(() -> {
                    clientPaymentDTO.setId(id);
                    clientPaymentRepository.save(clientPaymentEntity);
                    return modelMapper.map(clientPaymentEntity,ClientPaymentResponse.class);
                });
    }


    public void deleteAllData(){
        clientPaymentRepository.deleteAll();
    }

}
