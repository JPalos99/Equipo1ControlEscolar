/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Digis01.Equipo1ControlEscolar.SL;

import com.Digis01.Equipo1ControlEscolar.DL.AlumnoService;
import com.Digis01.Equipo1ControlEscolar.DL.ServiceAlumno;
import com.Digis01.Equipo1ControlEscolar.ML.Alumno;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/AlumoApi")
public class AlumnoRestController {

    ServiceAlumno serviceAlumno;
    AlumnoService alumnoService;

    @Autowired
    public AlumnoRestController(ServiceAlumno serviceAlumno, AlumnoService alumnoService) {
        this.serviceAlumno = serviceAlumno;
        this.alumnoService = alumnoService;
    }

    @GetMapping("/Listado")
    public ResponseEntity<List<Alumno>> Listado() {
        List<Alumno> alumnos = serviceAlumno.findAll();
        if (alumnos == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(alumnos, HttpStatus.OK);
        }
    }

    @GetMapping("/Add&Update/{id}")
    public ResponseEntity<Optional> obtenerAlumnoPorId(@PathVariable Long id) {
        Optional<Alumno> alumno = serviceAlumno.findById(id);
        if (!alumno.isPresent()) {

            alumno = Optional.empty();
            return new ResponseEntity<>(alumno, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(alumno, HttpStatus.OK);
        }
    }

    @PostMapping("/From")
    public ResponseEntity<Alumno> Form(@RequestBody Alumno alumno) {
        if (alumno.getIdalumno() == 0) {
            serviceAlumno.save(alumno);
            if (alumno == null) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(alumno, HttpStatus.OK);
            }
        } else {
            serviceAlumno.save(alumno);

            if (alumno == null) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(alumno, HttpStatus.OK);
            }

        }
    }

    @GetMapping("/elimina/{id}")
    public Map<String, String> Delete(@PathVariable Long id) {
        Map<String, String> map = new HashMap<>();
        serviceAlumno.deleteById(id);
        String ids = id.toString();
        map.put("Se elimino el  alumno con el id: ", ids);
        return map;
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @PostMapping("/AgregarSP")
    public void agregarAlumno(@RequestBody Alumno alumno) {
        int idalumno = alumno.getIdalumno();
        String nombre = alumno.getNombre();
        String apellidopaterno = alumno.getApellidopaterno();
        String apellidomaterno = alumno.getApellidomaterno();
        if (idalumno == 0) {
            alumnoService.agregar(nombre, apellidopaterno, apellidomaterno);
           
        } else {
            alumnoService.actualizar(idalumno, nombre, apellidopaterno, apellidomaterno);
            
        }
    }

    @GetMapping("/ListadoSP")
    public ResponseEntity<List<Alumno>> ListadoSP() {
        List<Alumno> alumnos = serviceAlumno.findAll();
        if (alumnos == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(alumnos, HttpStatus.OK);
        }
    }

    @GetMapping("/eliminaSP/{id}")
    public void DeleteSP(@PathVariable int id) {
        alumnoService.eliminar(id);
      
    }
}
