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

public class AlumnoMateria {
  
    private int idalumnomateria;
    
    private Alumno alumno;
   
    private Materia materia;

    public AlumnoMateria() {
    }

    public AlumnoMateria(int idalumnomateria) {
        this.idalumnomateria = idalumnomateria;
    }

    public int getIdalumnomateria() {
        return idalumnomateria;
    }

    public void setIdalumnomateria(int idalumnomateria) {
        this.idalumnomateria = idalumnomateria;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }
    
    
}
