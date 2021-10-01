package kz.dar.mailcoreapi.model;

import org.springframework.validation.annotation.Validated;



@Validated
public class ServicePayment {


    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    private ServiceType serviceType;

    private double amount;

    @Override
    public String toString() {
        return "ServicePayment{" +
                "serviceType=" + serviceType +
                ", amount=" + amount +
                '}';
    }
}
