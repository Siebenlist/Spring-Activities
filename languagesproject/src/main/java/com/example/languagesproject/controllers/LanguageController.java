package com.example.languagesproject.controllers;

import com.example.languagesproject.models.Language;
import com.example.languagesproject.services.LanguageService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LanguageController {
    private final LanguageService languageService;

    public LanguageController(LanguageService languageService) {
        this.languageService = languageService;
    }

    @GetMapping("/home")
    public String homePage(Model model){
        model.addAttribute("languages", languageService.findAll());
        return "home";
    }

    @PostMapping("/home")
    public String newLang(@Valid Language language, BindingResult result) {
        if (result.hasErrors()) {
            return "/home";
        }

        languageService.createLang(language);
        return "redirect:/home";
    }

    @GetMapping("/{id}")
    public String showById(@PathVariable("id") Long id, Model model){
        Language language = languageService.languageInfo(id);
        model.addAttribute("language", language);
        return "view";
    }

    @GetMapping("/edit/{id}")
    public String editPage(@PathVariable("id") Long id, Model model) {
        Language language = languageService.languageInfo(id);
        if (language == null) {
            throw new IllegalArgumentException("Invalid language Id:" + id);
        }
        model.addAttribute("language", language);
        return "edit";
    }

    @PostMapping("/edit/{id}")
    public String editLanguage(@PathVariable("id") Long id, @Valid @ModelAttribute("language") Language updatedLanguage, BindingResult result){
        if(result.hasErrors()){
            return "edit";
        } else {
            Language existingLanguage = languageService.languageInfo(id);
            existingLanguage.setName(updatedLanguage.getName());
            existingLanguage.setCreator(updatedLanguage.getCreator());
            existingLanguage.setVersion(updatedLanguage.getVersion());

            languageService.updateLanguage(existingLanguage);

            return "redirect:/home";
        }
    }

    @PostMapping("/delete/{id}")
    public String deleteById(@PathVariable("id") Long id){
        languageService.deleteLanguage(id);
        return "redirect:/home";
    }
}
