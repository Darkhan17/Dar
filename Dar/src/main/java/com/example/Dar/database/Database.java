package com.example.Dar.database;

import com.example.Dar.model.Package;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Vector;

public class Database {
    private static Database database;
    private Vector<Package> packages = new Vector<Package>();


    private Database(){}


    public static Database getInstance(){
        if (database == null){
            database = new Database();
        }
        return database;
    }

    public boolean addToDatabase(Package pac){
        return packages.add(pac);
    }

    public Vector<Package> getAllPackages(){
        return packages;
    }

    public Package getPackage(long id){
        for (Package pac: packages){
            if (pac.getId() == id) {
               return pac;
            }
        }
        return null;
    }




}
