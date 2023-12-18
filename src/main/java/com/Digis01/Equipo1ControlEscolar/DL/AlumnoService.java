/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Digis01.Equipo1ControlEscolar.DL;

import com.Digis01.Equipo1ControlEscolar.ML.Alumno;
import groovy.util.logging.Log;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author digis
 */
@Service
public class AlumnoService {
    
    private  ServiceAlumno serviceAlumno;

    @Autowired
    public AlumnoService(ServiceAlumno serviceAlumno) {
        this.serviceAlumno = serviceAlumno;
    }
    
    public List<Alumno> GetAll(){
        return serviceAlumno.findAll();
    }

    public void agregar(String nombre, String apellidopaterno, String apellidomaterno) {
        serviceAlumno.AlumnoAdd(nombre, apellidomaterno, apellidomaterno);
    }
    public void actualizar(int idalumno,String nombre, String apellidopaterno, String apellidomaterno) {
        serviceAlumno.AlumnoUpdate(idalumno,nombre, apellidopaterno, apellidomaterno);
    }
    
    public void eliminar(int idalumno){
        serviceAlumno.AlumnoDelete(idalumno);
    }
}
