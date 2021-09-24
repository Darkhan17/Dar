package kz.dar.serviceapi.repository.entiry;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ClientPaymentRepository extends ElasticsearchRepository<ClientPaymentEntity,String> {
    List<ClientPaymentEntity> findByClientId(String clientId);

}
