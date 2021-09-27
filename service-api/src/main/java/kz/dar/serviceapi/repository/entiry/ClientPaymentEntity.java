package kz.dar.serviceapi.repository.entiry;


import kz.dar.serviceapi.model.ServicePayment;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

@Getter
@Setter
@Document(indexName = "post-service-payment", createIndex = true)
public class ClientPaymentEntity {

    @Id
    @Field(type = FieldType.Keyword)
    private String id;

    @Field(type = FieldType.Keyword)
    private String address;

    @Field(type = FieldType.Keyword)
    private String clientId;

    private List<ServicePayment> servicePayments;

    @Field(type = FieldType.Keyword)
    private double totalAmount;


}
