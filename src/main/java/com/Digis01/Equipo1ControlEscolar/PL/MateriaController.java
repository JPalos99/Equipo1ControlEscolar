/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Digis01.Equipo1ControlEscolar.PL;

import com.Digis01.Equipo1ControlEscolar.BL.AlumnoBL;
import com.Digis01.Equipo1ControlEscolar.BL.MateriaBL;
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
        ResponseEntity<List<AlumnoBL>> responseAlumno = restTemplate.exchange(
                "http://localhost:8080/AlumoApi/Listado",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<AlumnoBL>>() {
        }
        );
        List<AlumnoBL> alumnos = responseAlumno.getBody();
        model.addAttribute("alumnos", alumnos);
        model.addAttribute("alumno", new AlumnoBL());
        return "PaginaMaterias";
    }

    @GetMapping("/form/{idMateria}")
    public String Form(@PathVariable int idMateria, Model model) {
        RestTemplate restTemplate = new RestTemplate();

        if (idMateria == 0) {
            model.addAttribute("materia", new MateriaBL());
            return "FormularioMateria";
        } else {
           
           String apiUrl =  "http://localhost:8080/MateriaApi/Add&Update/" + idMateria;
            ResponseEntity<Optional<MateriaBL>> response = restTemplate.exchange(
                    apiUrl,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<Optional<MateriaBL>>() {
            }
            );
            Optional<MateriaBL> materia = response.getBody();
            MateriaBL materias = materia.get();
            model.addAttribute("materia", materias);
        }
        return "FormularioMateria";

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
    
    
/////////////////////////////////////////////////////////////////////////////////////////////////////////

 @GetMapping("/listadoSP")
    private String listadoSP(Model model) {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "http://localhost:8080/MateriaApi/ListadoSP";
        ResponseEntity<List<MateriaBL>> response = restTemplate.exchange(
                apiUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<MateriaBL>>() {
        }
        );
        List<MateriaBL> materias = response.getBody();
        model.addAttribute("materias", materias);
        ResponseEntity<List<AlumnoBL>> responseAlumno = restTemplate.exchange(
                "http://localhost:8080/AlumoApi/ListadoSP",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<AlumnoBL>>() {
        }
        );
        List<AlumnoBL> alumnos = responseAlumno.getBody();
        model.addAttribute("alumnos", alumnos);
        model.addAttribute("alumno", new AlumnoBL());
        return "PaginaMateriasSP";
    }

    @GetMapping("/formSP/{idMateria}")
    public String FormSP (@PathVariable int idMateria, Model model) {
        RestTemplate restTemplate = new RestTemplate();

        if (idMateria == 0) {
            model.addAttribute("materia", new MateriaBL());
            return "FormularioMateriaSP";
        } else {
           
           String apiUrl =  "http://localhost:8080/MateriaApi/Add&Update/" + idMateria;
            ResponseEntity<Optional<MateriaBL>> response = restTemplate.exchange(
                    apiUrl,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<Optional<MateriaBL>>() {
            }
            );
            Optional<MateriaBL> materia = response.getBody();
            MateriaBL materias = materia.get();
            model.addAttribute("materia", materias);
        }
        return "FormularioMateriaSP";

    }

    @PostMapping("formSP")
    public String FormSP(@ModelAttribute("materia") MateriaBL materia, Model model) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String apiUrl = "http://localhost:8080/MateriaApi/AgregarSP";
        HttpEntity<MateriaBL> request
                = new HttpEntity<MateriaBL>(materia, headers);
        ResponseEntity<MateriaBL> response = restTemplate.exchange(
                apiUrl,
                HttpMethod.POST,
                request,
                new ParameterizedTypeReference<MateriaBL>() {
        }
        );
        return "redirect:/MateriaJPA/listadoSP";
    }

    @GetMapping("/EliminarMateriaSP/{idMateria}")
    public String DeleteSP(@PathVariable int idMateria) {
        RestTemplate RestTemplate = new RestTemplate();
        String apiUrl = "http://localhost:8080/MateriaApi/eliminaSP/" + idMateria;
        ResponseEntity<MateriaBL> response = RestTemplate.exchange(
                apiUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<MateriaBL>() {
        }
        );
        return "redirect:/MateriaJPA/listadoSP";
    }

}
