package kz.dar.mailcoreapi.model;

import java.util.List;

public class ClientPaymentDTO {
    private String id;

    private ClientModel client;


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

    public ClientModel getClient() {
        return client;
    }

    public void setClient(ClientModel client) {
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

    @Override
    public String toString() {
        return "ClientPaymentDTO{" +
                "id='" + id + '\'' +
                ", client=" + client +
                ", totalAmount=" + totalAmount +
                ", address='" + address + '\'' +
                ", servicePayments=" + servicePayments +
                '}';
    }



}