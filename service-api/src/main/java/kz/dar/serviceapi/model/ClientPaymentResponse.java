package kz.dar.serviceapi.model;

import java.util.List;

public class ClientPaymentResponse {

    private String id;

    private String clientId;

    private double totalAmount;

    private String address;

    private List<ServicePayment> servicePayments;

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

    public double getTotalAmount() {
        return totalAmount;
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
