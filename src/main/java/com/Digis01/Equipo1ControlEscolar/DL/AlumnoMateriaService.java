/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Digis01.Equipo1ControlEscolar.DL;

import com.Digis01.Equipo1ControlEscolar.ML.AlumnoMateria;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

/**
 *
 * @author digis
 */
@Service
public class AlumnoMateriaService {

    private ServiceAlumnoMateria serviceAlumnoMateria;

    @Autowired
    public AlumnoMateriaService(ServiceAlumnoMateria serviceAlumnoMateria) {
        this.serviceAlumnoMateria = serviceAlumnoMateria;
    }

    public List<AlumnoMateria> GetAll(){
        return serviceAlumnoMateria.findAll();
    }

    public void agregar(int idAlumno,int idMateria) {
        serviceAlumnoMateria.AlumMatAdd(idAlumno, idMateria);
    }
    public void actualizar(int idAlumnoMateria,int idAlumno,int idMateria) {
       serviceAlumnoMateria.AlumMatUpdate(idAlumnoMateria, idAlumno, idMateria);
    }
    
    public void eliminar(int idAlumnoMateria){
        serviceAlumnoMateria.MateriaDelete(idAlumnoMateria);
    }
    
}
