/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Digis01.Equipo1ControlEscolar.PL;

import com.Digis01.Equipo1ControlEscolar.BL.AlumnoMateriaBL;
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


@Controller
@RequestMapping("/AlumnoMateriaJPA")
public class AlumnoMateriaController {
    
    
    
    @GetMapping("/listado")
    private String listado(Model model) {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "http://localhost:8080/AlumnoMateriaApi/Listado";
        ResponseEntity<List<AlumnoMateriaBL>> response = restTemplate.exchange(
                apiUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<AlumnoMateriaBL>>() {
        }
        );
        List<AlumnoMateriaBL> alumnomaterias = response.getBody();
        model.addAttribute("alumnomaterias", alumnomaterias);
        return "PaginaAlumnoMateria";
    }

    @GetMapping("/form/{idAlumnoMateria}")
    public String Form(@PathVariable int idAlumnoMateria, Model model) {
        RestTemplate restTemplate = new RestTemplate();

        if (idAlumnoMateria == 0) {
            model.addAttribute("alumnomateria", new AlumnoMateriaBL());
            return "PaginaAlumnoMateria";
        } else {
            ResponseEntity<AlumnoMateriaBL> responseEntityAlumno = restTemplate.getForEntity("http://localhost:8080/AlumnoMateriaApi/Add&Update/" + idAlumnoMateria, AlumnoMateriaBL.class);

            model.addAttribute("alumnomateria", responseEntityAlumno);
        }
        return "";
    }

    @PostMapping("form")
    public String Form(@ModelAttribute("alumnomateria") AlumnoMateriaBL alumnomateria, Model model) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String apiUrl = "http://localhost:8080/AlumnoMateriaApi/From";
        HttpEntity<AlumnoMateriaBL> request
                = new HttpEntity<AlumnoMateriaBL>(alumnomateria, headers);
        ResponseEntity<AlumnoMateriaBL> response = restTemplate.exchange(
                apiUrl,
                HttpMethod.POST,
                request,
                new ParameterizedTypeReference<AlumnoMateriaBL>() {
        }
        );
        return "";
    }

    @GetMapping("/EliminarMateria/{idAlumnoMateria}")
    public String Delete(@PathVariable int idMateria) {
        RestTemplate RestTemplate = new RestTemplate();
        String apiUrl = "http://localhost:8080/AlumnoMateriaApi/elimina/" + idMateria;
        ResponseEntity<AlumnoMateriaBL> response = RestTemplate.exchange(
                apiUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<AlumnoMateriaBL>() {
        }
        );
        return "redirect:/AlumnoMateriaJPA/listado";
    }
}
