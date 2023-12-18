/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Digis01.Equipo1ControlEscolar.SL;

import com.Digis01.Equipo1ControlEscolar.DL.ServiceAlumnoMateria;
import com.Digis01.Equipo1ControlEscolar.ML.AlumnoMateria;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/AlumnoMateriaApi")
public class AlumnoMateriaRestController {

    ServiceAlumnoMateria serviceAlumnoMateria;

    public AlumnoMateriaRestController(ServiceAlumnoMateria serviceAlumnoMateria) {
        this.serviceAlumnoMateria = serviceAlumnoMateria;
    }

    @GetMapping("/Listado")
    public ResponseEntity<List<AlumnoMateria>> Listado() {
        List<AlumnoMateria> alumnosmaterias = serviceAlumnoMateria.findAll();
        if (alumnosmaterias == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(alumnosmaterias, HttpStatus.OK);
        }
    }

    @GetMapping("/Add&Update/{id}")
    public ResponseEntity<AlumnoMateria> obtenerAlumnoPorId(@PathVariable Long id) {
        return serviceAlumnoMateria.findById(id)
                .map(alumno -> new ResponseEntity<>(alumno, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/From")
    public ResponseEntity<AlumnoMateria> Form(@RequestBody AlumnoMateria alumnoMateria) {
        if (alumnoMateria.getIdalumnomateria() == 0) {
            serviceAlumnoMateria.save(alumnoMateria);
            if (alumnoMateria == null) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(alumnoMateria, HttpStatus.OK);
            }
        } else {
            serviceAlumnoMateria.save(alumnoMateria);

            if (alumnoMateria == null) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(alumnoMateria, HttpStatus.OK);
            }

        }
    }

    @GetMapping("/elimina/{id}")
    public Map<String, String> Delete(@PathVariable Long id) {
        Map<String, String> map = new HashMap<>();
        serviceAlumnoMateria.deleteById(id);
        String ids = id.toString();
        map.put("Se elimino  con el id: ", ids);
        return map;
    }
/////////////////////////////////////////////////////////////////////////////////////////////
    
    @PostMapping("/AgregarSP")
    public void agregarAlumno(@RequestBody AlumnoMateria alumnoMateria) {
        int idAlumnoMateria = alumnoMateria.getIdalumnomateria();
        int idMateria= alumnoMateria.getMateria().getIdmateria();
        int idAlumno= alumnoMateria.getAlumno().getIdalumno();
        if (idAlumnoMateria == 0) {
            serviceAlumnoMateria.AlumMatAdd(idAlumno, idMateria);
           
        } else {
            serviceAlumnoMateria.AlumMatUpdate(idAlumnoMateria, idAlumno, idMateria);
          
        }
    }

    @GetMapping("/ListadoSP")
    public ResponseEntity<List<AlumnoMateria>> ListadoSP() {
        List<AlumnoMateria> alumnos = serviceAlumnoMateria.findAll();
        if (alumnos == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(alumnos, HttpStatus.OK);
        }
    }

    @GetMapping("/eliminaSP/{id}")
    public void DeleteSP(@PathVariable int id) {
        serviceAlumnoMateria.MateriaDelete(id);
        
    }
   
}
