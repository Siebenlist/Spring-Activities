package com.example.relationships.services;

import com.example.relationships.models.Person;
import com.example.relationships.repositories.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    public List<Person> allPersons(){
        return personRepository.findAll();
    }

    public Person createPerson(Person p){
        return personRepository.save(p);
    }

    public Person findPersonById(Long id){
        Optional<Person> optionalPerson = personRepository.findById(id);
        return optionalPerson.orElse(null);
    }
}
