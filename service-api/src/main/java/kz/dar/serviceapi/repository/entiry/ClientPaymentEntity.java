package kz.dar.serviceapi.repository.entiry;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@Setter
@Document(indexName = "post-service", createIndex = true)
public class ClientPaymentEntity {

    @Id
    @Field(type = FieldType.Keyword)
    private String id;


    @Field(type = FieldType.Keyword)
    private String clientId;

    @Field(type = FieldType.Keyword)
    private double amount;

    @Field(type = FieldType.Keyword)
    private String serviceType;


}
