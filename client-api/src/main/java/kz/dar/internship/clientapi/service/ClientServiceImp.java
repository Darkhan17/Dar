package kz.dar.internship.clientapi.service;

import kz.dar.internship.clientapi.model.ClientDTO;
import kz.dar.internship.clientapi.model.ClientResponseModel;
import kz.dar.internship.clientapi.repository.ClientEntity;
import kz.dar.internship.clientapi.repository.ClientRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientServiceImp implements ClientService {


    @Autowired
    ClientRepository clientRepository;

    @Override
    public ClientResponseModel createAndUpdate(ClientDTO clientDTO) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        ClientEntity clientEntity = modelMapper.map(clientDTO,ClientEntity.class);

        clientEntity = clientRepository.save(clientEntity);

        ClientResponseModel clientResponseModel = modelMapper.map(clientEntity,ClientResponseModel.class);
        return clientResponseModel;

    }

    @Override
    public ClientResponseModel getClient(String id) {
        Optional<ClientEntity> clientEntity = clientRepository.findById(Long.parseLong(id));
        ClientEntity client = clientEntity.get();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        ClientResponseModel clientResponse = modelMapper.map(client,ClientResponseModel.class);
        return clientResponse;
    }


}
