package com.example.languagesproject.services;

import com.example.languagesproject.models.Language;
import com.example.languagesproject.repositories.LanguageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LanguageService {
    private final LanguageRepository languageRepository;

    public LanguageService(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    public List<Language> findAll(){
        return languageRepository.findAll();
    }

    public Language createLang(Language lang){
        return languageRepository.save(lang);
    }
    public Language languageInfo(Long id) {
        Optional<Language> optionalLanguage = languageRepository.findById(id);
        return optionalLanguage.orElse(null);
    }

    public void updateLanguage(Language language) {
        Optional<Language> existingLanguage = languageRepository.findById(language.getId());
        if (existingLanguage.isPresent()) {
            Language updatedLanguage = existingLanguage.get();
            updatedLanguage.setName(language.getName());
            updatedLanguage.setCreator(language.getCreator());
            updatedLanguage.setVersion(language.getVersion());

            languageRepository.save(updatedLanguage);
        }
    }

    public void deleteLanguage(Long id){
       if(languageRepository.existsById(id)){
           languageRepository.deleteById(id);
       }
    }
}
