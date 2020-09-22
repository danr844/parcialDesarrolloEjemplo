/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pokemon.ejb;

import co.edu.uniandes.csw.pokemon.entities.PokemonEntity;
import co.edu.uniandes.csw.pokemon.entities.ataqueEntity;
import co.edu.uniandes.csw.pokemon.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.pokemon.persistence.PokemonPersistence;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Daniel Alejandro Angel Fuertes
 */
@Stateless
public class PokemonLogic {
    private static final Logger LOGGER = Logger.getLogger(PokemonLogic.class.getName());

    @Inject
    private PokemonPersistence persistence;
    
    public PokemonEntity createPokemon(PokemonEntity pokemon) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación del pokemon.");
        if (persistence.find(pokemon.getId())!=null) {
            if(persistence.find(pokemon.getId()).getNombre().equals(pokemon.getNombre()))
            throw new BusinessLogicException("Ya existe un pokemon con el nombre especificado.");
        }

        if (pokemon.getPokeDebilidad().equals(pokemon.getPokeType())) {
            throw new BusinessLogicException("Un pokemon tipo" + pokemon.getPokeType() + "No puede tener la misma debilidad");
        }

        if (pokemon.getAtaques().isEmpty()) {
            throw new BusinessLogicException("El pokemon debe tener al menos 1 ataque.");
        }
        for(ataqueEntity ataque: pokemon.getAtaques()){
            if(ataque.getDanio()>100 || ataque.getDanio()<10)
            throw new BusinessLogicException("El ataque del pokemon debe estar entre 10 y 100.");
        }

        pokemon = persistence.create(pokemon);
        LOGGER.log(Level.INFO, "Termina proceso de creación del pokemon.");
        return pokemon;
    }
    
}
