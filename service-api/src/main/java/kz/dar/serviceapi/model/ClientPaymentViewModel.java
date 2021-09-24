package kz.dar.serviceapi.model;

public class ClientPaymentViewModel {
    private String id;

    private ClientResponseModel client;

    private double amount;

    private ServiceType serviceType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ClientResponseModel getClient() {
        return client;
    }

    public void setClient(ClientResponseModel client) {
        this.client = client;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }
}
