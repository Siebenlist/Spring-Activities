package com.example.relationships.controllers;

import com.example.relationships.models.License;
import com.example.relationships.models.Person;
import com.example.relationships.services.LicenseService;
import com.example.relationships.services.PersonService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class PersonController {
    private final PersonService personService;
    private final LicenseService licenseService;
    public PersonController(PersonService personService, LicenseService licenseService){
        this.personService = personService;
        this.licenseService = licenseService;
    }

    @GetMapping("/persons/new")
    public String personForm(@ModelAttribute("person") Person person){
        return "newPerson";
    }

    @PostMapping("/persons/new")
    public String createPerson(@Valid @ModelAttribute("person") Person person, BindingResult result){
        if(result.hasErrors()){
            return "newPerson";
        }
        personService.createPerson(person);
        return "redirect:/persons/new";
    }

    @GetMapping("/persons/{id}")
    public String viewPerson(@PathVariable("id") Long id, Model model){
        Person showPerson = personService.findPersonById(id);
        model.addAttribute("person", showPerson);
        License personLicense = showPerson.getLicense();
        model.addAttribute("personLicense", personLicense);
        return "viewPerson";
    }
}
