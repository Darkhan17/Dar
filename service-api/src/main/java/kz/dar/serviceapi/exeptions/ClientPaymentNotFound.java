package kz.dar.serviceapi.exeptions;

public class ClientPaymentNotFound extends RuntimeException{
    public ClientPaymentNotFound(String id){
        super("can't find client's payment with id " + id);
    }
}
