/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.manager;

import com.project.manager.models.Person;
import com.project.manager.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

/**
 *
 * @author seba
 */
@Component
public class Inject {
    
    @Autowired
    private PersonRepository personRepository;
    
    @PostConstruct
    public void inject() {
        personRepository.save(new Person("name", "surname"));
    }
    
}
