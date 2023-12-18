/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Digis01.Equipo1ControlEscolar.DL;

import com.Digis01.Equipo1ControlEscolar.ML.AlumnoMateria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;


public interface ServiceAlumnoMateria extends JpaRepository<AlumnoMateria, Long> {
   
     @Procedure(name = "AlumMatAdd")
    void AlumMatAdd (
            
            @Param("id_alumno") int idAlumno,
            @Param("id_materia") int idMateria


    );

    @Procedure(name = "AlumMatUpdate")
    void AlumMatUpdate(
            @Param("id_alumnomateria") int idAlumnoMateria,
            @Param("id_alumno") int idAlumno,
            @Param("id_materia") int idMateria       
    );

    @Procedure(name = "AlumMatDelete")
    void MateriaDelete(@Param("idalumnomateriaa ") int idalumnomateriaa);
    
}
