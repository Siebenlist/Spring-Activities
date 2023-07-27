package com.example.holahumano;

import ch.qos.logback.core.model.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/")
    public String index(@RequestParam(value="name", required=false) String name,
                        @RequestParam(value="apellido", required=false) String apellido){
        if(name == null){
            return "Hello Human";
        } else {
            return "Hello " + name + " " + apellido;
        }
    }
}
