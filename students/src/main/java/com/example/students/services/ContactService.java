package com.example.students.services;

import com.example.students.models.Contact;
import com.example.students.repositories.ContactRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ContactService {
    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository){
        this.contactRepository = contactRepository;
    }

    public List<Contact> allContacts(){
        return contactRepository.findAll();
    }

    public Contact createContact(Contact c){
        return contactRepository.save(c);
    }

    public Contact findContactById(Long id){
        Optional<Contact> optionalContact = contactRepository.findById(id);
        return optionalContact.orElse(null);
    }
}
