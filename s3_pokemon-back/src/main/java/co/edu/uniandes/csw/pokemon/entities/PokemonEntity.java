/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pokemon.entities;

import co.edu.uniandes.csw.pokemon.constants.PokemonType;
import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Daniel Alejandro Angel Fuertes
 */
@Entity
public class PokemonEntity extends BaseEntity implements Serializable {

   
   
private String nombre; 
private String descripcion; 
private Double peso;
private Double altura; 
private PokemonType pokeType;
private PokemonType pokeDebilidad;
private Long pokeID;
@PodamExclude 
@ManyToMany (mappedBy = "pokemones")
private ArrayList<ataqueEntity> ataques;



 /**
     * @return the ataques
     */
    public ArrayList<ataqueEntity> getAtaques() {
        return ataques;
    }

    /**
     * @param ataques the ataques to set
     */
    public void setAtaques(ArrayList<ataqueEntity> ataques) {
        this.ataques = ataques;
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
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the peso
     */
    public Double getPeso() {
        return peso;
    }

    /**
     * @param peso the peso to set
     */
    public void setPeso(Double peso) {
        this.peso = peso;
    }

    /**
     * @return the altura
     */
    public Double getAltura() {
        return altura;
    }

    /**
     * @param altura the altura to set
     */
    public void setAltura(Double altura) {
        this.altura = altura;
    }

    /**
     * @return the pokeType
     */
    public PokemonType getPokeType() {
        return pokeType;
    }

    /**
     * @param pokeType the pokeType to set
     */
    public void setPokeType(PokemonType pokeType) {
        this.pokeType = pokeType;
    }

    /**
     * @return the pokeDebilidad
     */
    public PokemonType getPokeDebilidad() {
        return pokeDebilidad;
    }

    /**
     * @param pokeDebilidad the pokeDebilidad to set
     */
    public void setPokeDebilidad(PokemonType pokeDebilidad) {
        this.pokeDebilidad = pokeDebilidad;
    }

    /**
     * @return the pokeID
     */
    public Long getPokeID() {
        return pokeID;
    }

    /**
     * @param pokeID the pokeID to set
     */
    public void setPokeID(Long pokeID) {
        this.pokeID = pokeID;
    }
}



 