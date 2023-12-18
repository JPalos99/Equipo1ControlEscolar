package com.Digis01.Equipo1ControlEscolar.DL;

import com.Digis01.Equipo1ControlEscolar.ML.Alumno;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

public interface ServiceAlumno extends JpaRepository<Alumno, Long> {

    @Procedure(name = "AlumnoAdd")
    void AlumnoAdd(
           
            @Param("nombre") String nombre,
            @Param("apellidopaterno") String apellidoPaterno,
            @Param("apellidomaterno") String apellidoMaterno
    );

    @Procedure(name = "AlumnoUpdate")
    void AlumnoUpdate(
            @Param("idalumnoo") int idalumno,
            @Param("nombreparametro") String nombre,
            @Param("apellidopaterno") String apellidopaterno,
            @Param("apellidomaterno") String apellidomaterno
    );

    @Procedure(name = "AlumnoDelete")
    void AlumnoDelete(@Param("idalumnoo") int idalumnoo);
}
