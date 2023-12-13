/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Digis01.Equipo1ControlEscolar.DL;

import com.Digis01.Equipo1ControlEscolar.ML.AlumnoMateria;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ServiceAlumnoMateria extends JpaRepository<AlumnoMateria, Long> {
   
}
