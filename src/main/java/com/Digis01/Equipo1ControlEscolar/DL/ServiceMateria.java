/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Digis01.Equipo1ControlEscolar.DL;

import com.Digis01.Equipo1ControlEscolar.ML.Materia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author digis
 */
public interface ServiceMateria extends JpaRepository<Materia, Long> {
    
    @Procedure(name = "MateriaAdd")
    void MateriaAdd(
            @Param("nombre") String nombre,
            @Param("costo") double costo       
    );
    
    @Procedure(name = "MateriaUpdate")
    void MateriaUpdate(
            @Param("idmateriaa") int idmateria,
            @Param("nombree") String nombree,
            @Param("costoo") double costo        
    );

    @Procedure(name = "MateriaDelete")
    void MateriaDelete(@Param("idmateriaa") int idmateriaa);
    
    
    
}
