/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Digis01.Equipo1ControlEscolar.PL;

import com.Digis01.Equipo1ControlEscolar.BL.AlumnoBL;
import com.Digis01.Equipo1ControlEscolar.BL.MateriaBL;
import com.Digis01.Equipo1ControlEscolar.ML.Alumno;

import java.util.List;
import java.util.Optional;
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

    @GetMapping("/Inicio")
    public String inicio(){
    return"PaginaInicio";
            
    }
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
        ResponseEntity<List<MateriaBL>> responseMateria = restTemplate.exchange(
                "http://localhost:8080/MateriaApi/Listado",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<MateriaBL>>() {
        }
        );
        List<MateriaBL> materias = responseMateria.getBody();
        model.addAttribute("materias", materias);
        model.addAttribute("materia", new MateriaBL());
        return "PaginaAlumnos";
    }

    @GetMapping("/form/{idalumno}")
    public String Form(@PathVariable int idalumno, Model model) {
        RestTemplate restTemplate = new RestTemplate();

        if (idalumno == 0) {
            model.addAttribute("alumno", new Alumno());
            return "FormularioAlumno";
        } else {

            String apiUrl = "http://localhost:8080/AlumoApi/Add&Update/" + idalumno;
            ResponseEntity<Optional<Alumno>> response = restTemplate.exchange(
                    apiUrl,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<Optional<Alumno>>() {
            }
            );
            Optional<Alumno> alumno = response.getBody();
            Alumno alumnos = alumno.get();
            model.addAttribute("alumno", alumnos);
        }
        return "FormularioAlumno";
    }

    @PostMapping("/form")
    public String Form(@ModelAttribute("alumno") Alumno alumno, Model model) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String apiUrl = "http://localhost:8080/AlumoApi/From";
        HttpEntity<Alumno> request
                = new HttpEntity<Alumno>(alumno, headers);
        ResponseEntity<Alumno> response = restTemplate.exchange(
                apiUrl,
                HttpMethod.POST,
                request,
                new ParameterizedTypeReference<Alumno>() {
        }
        );
        return "redirect:/AlumnoJPA/listado";
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
        return "redirect:/AlumnoJPA/listado";
    }
}
