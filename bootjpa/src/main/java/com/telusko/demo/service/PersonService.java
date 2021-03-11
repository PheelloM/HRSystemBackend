package com.telusko.demo.service;

import com.telusko.demo.model.Person;
import com.telusko.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonRepository repo;

    public Person save(Person p){
        return repo.save(p);
    }
}
