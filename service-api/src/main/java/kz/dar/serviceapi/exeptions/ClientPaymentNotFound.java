package kz.dar.serviceapi.exeptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ClientPaymentNotFound extends RuntimeException{
    public ClientPaymentNotFound(String id){
        super("can't find client's payment with id " + id);
    }
}
