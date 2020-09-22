/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pokemon.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author DAniel Alejandro Angel Fuertes
 */
@Entity 
public class ataqueEntity extends BaseEntity implements Serializable {

 
   
    private String nombre;
    private Integer danio;
    @PodamExclude 
    @ManyToMany
    private List<PokemonEntity> pokemones;
    
    
       /**
     * @return the pokemones
     */
    public List<PokemonEntity> getPokemones() {
        return pokemones;
    }

    /**
     * @param pokemones the pokemones to set
     */
    public void setPokemones(List<PokemonEntity> pokemones) {
        this.pokemones = pokemones;
    }

     /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the danio
     */
    public Integer getDanio() {
        return danio;
    }

    /**
     * @param danio the danio to set
     */
    public void setDanio(Integer danio) {
        this.danio = danio;
    }

    
}
