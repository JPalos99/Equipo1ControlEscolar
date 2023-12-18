/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Digis01.Equipo1ControlEscolar.SL;

import com.Digis01.Equipo1ControlEscolar.DL.ServiceMateria;

import com.Digis01.Equipo1ControlEscolar.ML.Materia;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author digis
 */
@RestController
@RequestMapping("MateriaApi")
public class MateriaRestController {

    ServiceMateria serviceMateria;

    public MateriaRestController(ServiceMateria serviceMateria) {
        this.serviceMateria = serviceMateria;
    }

    @GetMapping("/Listado")
    public ResponseEntity<List<Materia>> Listado() {
        List<Materia> materias = serviceMateria.findAll();
        if (materias == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(materias, HttpStatus.OK);
        }
    }

    @GetMapping("/Add&Update/{id}")
    public ResponseEntity<Optional> obtenerAlumnoPorId(@PathVariable Long id) {
        Optional<Materia> materia = serviceMateria.findById(id);

        if (!materia.isPresent()) {

            materia = Optional.empty();
            return new ResponseEntity<>(materia, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(materia, HttpStatus.OK);
        }
    }

    @PostMapping("/From")
    public ResponseEntity<Materia> Form(@RequestBody Materia materia) {
        if (materia.getIdmateria() == 0) {
            serviceMateria.save(materia);
            if (materia == null) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(materia, HttpStatus.OK);
            }
        } else {
            serviceMateria.save(materia);

            if (materia == null) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(materia, HttpStatus.OK);
            }

        }
    }

    @GetMapping("/elimina/{id}")
    public Map<String, String> Delete(@PathVariable Long id) {
        Map<String, String> map = new HashMap<>();
        serviceMateria.deleteById(id);
        String ids = id.toString();
        map.put("Se elimino la materia con el id: ", ids);
        return map;
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////
    @PostMapping("/AgregarSP")
    public void agregarAlumno(@RequestBody Materia materia) {
        int idmateria = materia.getIdmateria();
        String nombre = materia.getNombre();
        double costo = materia.getCosto();

        if (idmateria == 0) {
            serviceMateria.MateriaAdd(nombre, costo);
            
        } else {
            serviceMateria.MateriaUpdate(idmateria, nombre, costo);
           
        }
    }

    @GetMapping("/ListadoSP")
    public ResponseEntity<List<Materia>> ListadoSP() {
        List<Materia> alumnos = serviceMateria.findAll();
        if (alumnos == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(alumnos, HttpStatus.OK);
        }
    }

    @GetMapping("/eliminaSP/{id}")
    public void DeleteSP(@PathVariable int id) {

        serviceMateria.MateriaDelete(id);
       
    }

}
