package com.example.Dar.repository;

import com.example.Dar.model.PackageResponse;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PackageRepository extends CrudRepository<PackageEntity, Long> {

    List<PackageEntity> findBySenderId(String sender);

}
