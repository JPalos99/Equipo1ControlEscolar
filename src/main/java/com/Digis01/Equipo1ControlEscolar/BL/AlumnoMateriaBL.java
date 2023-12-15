/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Digis01.Equipo1ControlEscolar.BL;

import com.Digis01.Equipo1ControlEscolar.ML.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

/**
 *
 * @author digis
 */

public class AlumnoMateriaBL {
  
    private int idalumnomateria;
    
    private AlumnoBL alumno;
   
    private MateriaBL materia;

    public AlumnoMateriaBL() {
    }

    public AlumnoMateriaBL(int idalumnomateria) {
        this.idalumnomateria = idalumnomateria;
    }

    public int getIdalumnomateria() {
        return idalumnomateria;
    }

    public void setIdalumnomateria(int idalumnomateria) {
        this.idalumnomateria = idalumnomateria;
    }

    public AlumnoBL getAlumno() {
        return alumno;
    }

    public void setAlumno(AlumnoBL alumno) {
        this.alumno = alumno;
    }

    public MateriaBL getMateria() {
        return materia;
    }

    public void setMateria(MateriaBL materia) {
        this.materia = materia;
    }
    
    
}
