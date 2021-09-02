package com.example.Dar.controller;


import com.example.Dar.database.Database;
import com.example.Dar.model.Package;
import com.example.Dar.model.Status;
import org.springframework.beans.factory.parsing.Problem;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Vector;

@RestController
@RequestMapping("/post")
public class PostController {

    Database database  = Database.getInstance();

    @GetMapping()
    public Vector<Package> check(){
        return database.getAllPackages();
    }
    @GetMapping("/all")
    public ResponseEntity<StringBuffer> getAllStatus(){
        StringBuffer stringBuffer = new StringBuffer();
       for ( Package pac : database.getAllPackages()){
           stringBuffer.append(pac.toString()+ "\r");
       }
       return new ResponseEntity<StringBuffer>(stringBuffer,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Package> checkStatus(@PathVariable long id){
        Package pack = database.getPackage(id);
        return new ResponseEntity<Package>(pack,HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Package> createPackage(@RequestBody Package pack){
        pack.setStatus(Status.IN_PROGRESS);
        database.addToDatabase(pack);
        return new ResponseEntity<Package>(pack,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Package> updatePackage(@RequestBody Package pack,@PathVariable long id){
        Package mail = database.getPackage(id);
        mail.setDescription(pack.getDescription());
        return new ResponseEntity<Package>(mail,HttpStatus.OK);
    }

    @PutMapping("/{id}/complete")
    ResponseEntity<?> complete(@PathVariable Long id) {

        Package order = database.getPackage(id);

        if (order != null && order.getStatus() == Status.IN_PROGRESS) {
            order.setStatus(Status.COMPLETED);
            return new ResponseEntity<Package>(order,HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
    }


}
