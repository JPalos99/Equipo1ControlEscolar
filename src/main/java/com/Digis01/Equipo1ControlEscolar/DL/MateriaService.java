/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Digis01.Equipo1ControlEscolar.DL;

import com.Digis01.Equipo1ControlEscolar.ML.Materia;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author digis
 */

@Service
public class MateriaService {
    private ServiceMateria serviceMateria;

    @Autowired

    public MateriaService(ServiceMateria serviceMateria ) {
        this.serviceMateria = serviceMateria;
    }
    
     public List<Materia> GetAll(){
        return serviceMateria.findAll();
    }

    public void agregar(int costo,String nombre) {
        serviceMateria.MateriaAdd(nombre, costo);
    }
    public void actualizar(int idmateriaa,String nombree,int costo ) {
        serviceMateria.MateriaUpdate(idmateriaa, nombree, costo);
    }
    
    public void eliminar(int idmateriaa){
        serviceMateria.MateriaDelete(idmateriaa);
    }
   
}
