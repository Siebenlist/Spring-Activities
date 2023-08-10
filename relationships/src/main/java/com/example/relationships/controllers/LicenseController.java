package com.example.relationships.controllers;

import com.example.relationships.models.License;
import com.example.relationships.models.Person;
import com.example.relationships.services.LicenseService;
import com.example.relationships.services.PersonService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
public class LicenseController {
    private final LicenseService licenseService;
    private final PersonService personService;

    public LicenseController(LicenseService licenseService, PersonService personService){
        this.licenseService = licenseService;
        this.personService = personService;
    }

    @GetMapping("/licenses/new")
    public String licenseForm(Model model){
        model.addAttribute("license", new License());
        List<Person> allPersons = personService.allPersons();
        model.addAttribute("persons", allPersons);
        return "newLicense";
    }

    @PostMapping("/licenses/new")
    public String createLicenses(@RequestParam("personName") Long personName,
                                 @RequestParam("state") String state,
                                 @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date){
        License license = new License();
        Person person = personService.findPersonById(personName);

        if (person != null){
            license.setPerson(person);
        }

        license.setState(state);
        license.setExpirationDate(date);

        Long nextLicenseId = licenseService.getNextLicenseId();
        license.setIdAndFormatNumber(nextLicenseId);
        licenseService.createLicense(license);

        return "redirect:/licenses/new";
    }
}
