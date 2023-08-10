package com.example.relationships.services;

import com.example.relationships.models.License;
import com.example.relationships.repositories.LicenseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LicenseService {
    private final LicenseRepository licenseRepository;

    public LicenseService(LicenseRepository licenseRepository){
        this.licenseRepository = licenseRepository;
    }

    public List<License> allLicenses(){
        return licenseRepository.findAll();
    }

    public License createLicense(License p){
        return licenseRepository.save(p);
    }

    public License findPersonById(Long id){
        Optional<License> optionalLicense = licenseRepository.findById(id);
        if(optionalLicense.isPresent()){
            return optionalLicense.get();
        } else {
            return null;
        }
    }

    public Long getNextLicenseId() {
        Long lastLicenseId = licenseRepository.findMaxId();
        return (lastLicenseId != null) ? lastLicenseId + 1 : 1;
    }
}
