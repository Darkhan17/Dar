package com.example.Dar.service;

import com.example.Dar.model.PackageDTO;
import com.example.Dar.model.PackageResponse;
import com.example.Dar.repository.PackageEntity;
import com.example.Dar.repository.PackageRepository;
import org.dom4j.rule.Mode;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PackageServiceImpl implements PackageService {


    @Autowired
    PackageRepository packageRepository;




    @Override
    public PackageResponse getPackage(String id) {
        Optional<PackageEntity> packageEntity = packageRepository.findById(Long.parseLong(id));
        PackageEntity packageEntity1 = packageEntity.get();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        PackageResponse packageResponse = modelMapper.map(packageEntity1, PackageResponse.class);
        return packageResponse;
    }

    @Override
    public List<PackageResponse> getAllPackage() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return ((List<PackageEntity>) packageRepository.findAll()).stream().map(pack ->modelMapper.map(pack, PackageResponse.class)).collect(Collectors.toList());
    }



    @Override
    public PackageResponse createAndUpdatePackage(PackageDTO packageDTO) {

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        PackageEntity packageEntity = modelMapper.map(packageDTO, PackageEntity.class);


        packageEntity = packageRepository.save(packageEntity);

        PackageResponse packageResponse = modelMapper.map(packageEntity, PackageResponse.class);
        packageResponse.setId(packageEntity.getId().toString());
        return packageResponse;
    }

    @Override
    public List<PackageResponse> getPackageListBySender(String sender){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        return ((List<PackageEntity>) packageRepository.findBySenderId(sender)).stream().map(pack ->modelMapper.map(pack, PackageResponse.class)).collect(Collectors.toList());

    }



}
