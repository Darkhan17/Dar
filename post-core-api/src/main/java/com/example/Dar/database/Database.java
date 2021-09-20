package com.example.Dar.database;

import com.example.Dar.model.PackageModelRequest;

import java.util.Vector;

public class Database {
    private static Database database;
    private Vector<PackageModelRequest> packages = new Vector<PackageModelRequest>();


    private Database(){}


    public static Database getInstance(){
        if (database == null){
            database = new Database();
        }
        return database;
    }

    public boolean addToDatabase(PackageModelRequest pac){
        return packages.add(pac);
    }

    public Vector<PackageModelRequest> getAllPackages(){
        return packages;
    }

    public PackageModelRequest getPackage(long id){
        for (PackageModelRequest pac: packages){
            if (pac.getId().equals(id)) {
               return pac;
            }
        }
        return null;
    }




}
