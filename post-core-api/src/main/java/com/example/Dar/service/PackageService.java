package com.example.Dar.service;

import com.example.Dar.model.PackageDTO;
import com.example.Dar.model.PackageResponse;

import java.util.List;

public interface PackageService {


    public PackageResponse getPackage(String id);

    public List<PackageResponse> getAllPackage();

    public PackageResponse createAndUpdatePackage(PackageDTO packageDTO);


    public List<PackageResponse> getPackageListBySender(String sender);






}
