package com.telusko.demo.controller;

import com.telusko.demo.model.Person;
import com.telusko.demo.model.Qualification;
import com.telusko.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;

@RestController
public class PersonController {

    @Autowired
    private PersonService service;

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/details")
    public Person save(@RequestBody Person p){
        return service.save(p);
    }
}
