/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Digis01.Equipo1ControlEscolar.PL;


import com.Digis01.Equipo1ControlEscolar.BL.AlumnoBL;

import java.util.List;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author digis
 */
@Controller
@RequestMapping("/AlumnoJPA")
public class AlumnoController {
    
    @GetMapping("/listado")
    private String listadoPasajeros(Model model) {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "http://localhost:8080/AlumoApi/Listado";
        ResponseEntity<List<AlumnoBL>> response = restTemplate.exchange(
                apiUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<AlumnoBL>>() {
        }
        );
        List<AlumnoBL> alumnos = response.getBody();
        model.addAttribute("alumnos", alumnos);
        return "";
    }
    
    @GetMapping("/form/{idAlumno}")
    public String Form(@PathVariable int idAlumno, Model model) {
        RestTemplate restTemplate = new RestTemplate();
        
        if (idAlumno == 0) {
            model.addAttribute("alumno", new AlumnoBL());
            return "Form";
        } else {
            ResponseEntity<AlumnoBL> responseEntityAlumno = restTemplate.getForEntity("http://localhost:8080/AlumoApi/Add&Update/" + idAlumno,AlumnoBL.class);
           
             model.addAttribute("alumno", responseEntityAlumno);
        }
        return "";
    }
    @PostMapping("form")
    public String Form(@ModelAttribute("alumno") AlumnoBL alumno, Model model) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String apiUrl = "http://localhost:8080/AlumoApi/From";
        HttpEntity<AlumnoBL> request
                = new HttpEntity<AlumnoBL>(alumno, headers);
        ResponseEntity<AlumnoBL> response = restTemplate.exchange(
                apiUrl,
                HttpMethod.POST,
                request,
                new ParameterizedTypeReference<AlumnoBL>() {
        }
        );
        return "";
    }
    
     @GetMapping("/EliminarAlumno/{idAlumno}")
    public String Delete(@PathVariable int idAlumno) {
        RestTemplate RestTemplate = new RestTemplate();
        String apiUrl = "http://localhost:8080/AlumoApi/elimina/" + idAlumno;
        ResponseEntity<AlumnoBL> response = RestTemplate.exchange(
                apiUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<AlumnoBL>() {
        }
        );
        return "";
    }
}
