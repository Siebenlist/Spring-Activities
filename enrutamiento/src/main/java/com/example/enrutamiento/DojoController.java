package com.example.enrutamiento;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DojoController {
    @RequestMapping("/{dojo}")
    public String dojo(@PathVariable("dojo") String dojo){
        if(dojo.equals("dojo")){
            return "¡El Dojo es increíble!";
        } else if(dojo.equals("burbank-dojo")){
            return "El Dojo Burbank está localizado al sur de California";
        } else if (dojo.equals("san-jose")){
            return "El Dojo SJ es el cuartel general";
        }
        return dojo;
    }
}
