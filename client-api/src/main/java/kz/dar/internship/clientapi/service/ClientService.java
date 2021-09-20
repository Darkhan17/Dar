package kz.dar.internship.clientapi.service;

import kz.dar.internship.clientapi.model.ClientDTO;
import kz.dar.internship.clientapi.model.ClientResponseModel;


public interface ClientService {


    public ClientResponseModel createAndUpdate(ClientDTO clientDTO);

    public ClientResponseModel getClient(String id);

}
