/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Digis01.Equipo1ControlEscolar.PL;

import com.Digis01.Equipo1ControlEscolar.BL.MateriaBL;
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
@RequestMapping("/MateriaJPA")
public class MateriaController {

    @GetMapping("/listado")
    private String listadoPasajeros(Model model) {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "http://localhost:8080/MateriaApi/Listado";
        ResponseEntity<List<MateriaBL>> response = restTemplate.exchange(
                apiUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<MateriaBL>>() {
        }
        );
        List<MateriaBL> materias = response.getBody();
        model.addAttribute("materias", materias);
        return "PaginaMaterias";
    }

    @GetMapping("/form/{idMateria}")
    public String Form(@PathVariable int idMateria, Model model) {
        RestTemplate restTemplate = new RestTemplate();

        if (idMateria == 0) {
            model.addAttribute("materia", new MateriaBL());
            return "FormularioMateria";
        } else {
            ResponseEntity<MateriaBL> responseEntityAlumno = restTemplate.getForEntity("http://localhost:8080/MateriaApi/Add&Update/" + idMateria, MateriaBL.class);

            model.addAttribute("materia", responseEntityAlumno);
        }
        return "redirect:/MateriaJPA/listado";
    }

    @PostMapping("form")
    public String Form(@ModelAttribute("materia") MateriaBL materia, Model model) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String apiUrl = "http://localhost:8080/MateriaApi/From";
        HttpEntity<MateriaBL> request
                = new HttpEntity<MateriaBL>(materia, headers);
        ResponseEntity<MateriaBL> response = restTemplate.exchange(
                apiUrl,
                HttpMethod.POST,
                request,
                new ParameterizedTypeReference<MateriaBL>() {
        }
        );
        return "redirect:/MateriaJPA/listado";
    }

    @GetMapping("/EliminarMateria/{idMateria}")
    public String Delete(@PathVariable int idMateria) {
        RestTemplate RestTemplate = new RestTemplate();
        String apiUrl = "http://localhost:8080/MateriaApi/elimina/" + idMateria;
        ResponseEntity<MateriaBL> response = RestTemplate.exchange(
                apiUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<MateriaBL>() {
        }
        );
        return "redirect:/MateriaJPA/listado";
    }
}
