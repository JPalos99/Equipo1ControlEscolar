/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Digis01.Equipo1ControlEscolar.PL;

import com.Digis01.Equipo1ControlEscolar.BL.AlumnoBL;
import com.Digis01.Equipo1ControlEscolar.BL.AlumnoMateriaBL;
import com.Digis01.Equipo1ControlEscolar.BL.MateriaBL;
import com.Digis01.Equipo1ControlEscolar.ML.AlumnoMateria;
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
    private String listadoPasajeros(Model model) {
        RestTemplate restTemplate = new RestTemplate();

        String apiUrl = "http://localhost:8080/AlumnoMateriaApi/Listado";
        ResponseEntity<List<AlumnoMateria>> response = restTemplate.exchange(
                apiUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<AlumnoMateria>>() {
        }
        );
        List<AlumnoMateria> alumnomaterias = response.getBody();
        model.addAttribute("alumnomaterias", alumnomaterias);

        String apiUrlAlumno = "http://localhost:8080/AlumoApi/Listado";
        ResponseEntity<List<AlumnoBL>> responseAlumo = restTemplate.exchange(
                apiUrlAlumno,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<AlumnoBL>>() {
        }
        );
        List<AlumnoBL> alumnos = responseAlumo.getBody();
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

        return "PaginaAlumnoMateria";
    }

    @GetMapping("/form/{idAlumnoMateria}")
    public String Form(@PathVariable int idAlumnoMateria, Model model) {
        RestTemplate restTemplate = new RestTemplate();

        if (idAlumnoMateria == 0) {
            model.addAttribute("alumnomateria", new AlumnoMateriaBL());
            return "Form";
        } else {
            ResponseEntity<AlumnoMateriaBL> responseEntityAlumno = restTemplate.getForEntity("http://localhost:8080/AlumnoMateriaApi/Add&Update/" + idAlumnoMateria, AlumnoMateriaBL.class);

            model.addAttribute("alumnomateria", responseEntityAlumno);
        }
        return "";
    }

    @GetMapping("/EliminarMateria/{idalumnomateria}")
    public String Delete(@PathVariable int idalumnomateria) {
        RestTemplate RestTemplate = new RestTemplate();
        String apiUrl = "http://localhost:8080/AlumnoMateriaApi/elimina/" + idalumnomateria;
        ResponseEntity<AlumnoMateriaBL> response = RestTemplate.exchange(
                apiUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<AlumnoMateriaBL>() {
        }
        );
        return "redirect:/AlumnoMateriaJPA/listado";
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    @GetMapping("/listadoSP")
    private String listadoSP(Model model) {
        RestTemplate restTemplate = new RestTemplate();

        String apiUrl = "http://localhost:8080/AlumnoMateriaApi/ListadoSP";
        ResponseEntity<List<AlumnoMateria>> response = restTemplate.exchange(
                apiUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<AlumnoMateria>>() {
        }
        );
        List<AlumnoMateria> alumnomaterias = response.getBody();
        model.addAttribute("alumnomaterias", alumnomaterias);

        String apiUrlAlumno = "http://localhost:8080/AlumoApi/ListadoSP";
        ResponseEntity<List<AlumnoBL>> responseAlumo = restTemplate.exchange(
                apiUrlAlumno,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<AlumnoBL>>() {
        }
        );
        List<AlumnoBL> alumnos = responseAlumo.getBody();
        model.addAttribute("alumnos", alumnos);

        ResponseEntity<List<MateriaBL>> responseMateria = restTemplate.exchange(
                "http://localhost:8080/MateriaApi/ListadoSP",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<MateriaBL>>() {
        }
        );
        List<MateriaBL> materias = responseMateria.getBody();
        model.addAttribute("materias", materias);
        model.addAttribute("materia", new MateriaBL());

        return "PaginaAlumnoMateriaSP";
    }

    
    @GetMapping("/EliminarMateriaSP/{idalumnomateria}")
    public String DeleteSP(@PathVariable int idalumnomateria) {
        RestTemplate RestTemplate = new RestTemplate();
        String apiUrl = "http://localhost:8080/AlumnoMateriaApi/eliminaSP/" + idalumnomateria;
        ResponseEntity<AlumnoMateriaBL> response = RestTemplate.exchange(
                apiUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<AlumnoMateriaBL>() {
        }
        );
        return "redirect:/AlumnoMateriaJPA/listadoSP";
    }

}
