package kz.dar.serviceapi.model;

import java.util.List;

public class ClientPaymentViewModel {
    private String id;

    private ClientResponseModel client;


    private double totalAmount;

    private String address;

    private List<ServicePayment> servicePayments;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public double getTotalAmount() {
        return totalAmount;
    }

    public ClientResponseModel getClient() {
        return client;
    }

    public void setClient(ClientResponseModel client) {
        this.client = client;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<ServicePayment> getServicePayments() {
        return servicePayments;
    }

    public void setServicePayments(List<ServicePayment> servicePayments) {
        this.servicePayments = servicePayments;
    }
}
