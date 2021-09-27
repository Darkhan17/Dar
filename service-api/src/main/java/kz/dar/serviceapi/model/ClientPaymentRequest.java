package kz.dar.serviceapi.model;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.validation.constraints.NotNull;
import java.util.List;

public class ClientPaymentRequest {

    private String id;

    @NotNull(message = "ClientId can't be null")
    private String clientId;

    private String totalAmount;

    @NotNull(message = "Services can't be null")
    private List<ServicePayment> servicePayments;

    @NotNull(message = "Address can't be null")
    private String address;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<ServicePayment> getServicePayments() {
        return servicePayments;
    }

    public void setServicePayments(List<ServicePayment> servicePayments) {
        this.servicePayments = servicePayments;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
