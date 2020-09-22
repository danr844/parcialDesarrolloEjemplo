/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pokemon.persistence;

import co.edu.uniandes.csw.pokemon.entities.PokemonEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author DAniel Alejandro Angel Fuertes
 */
public class PokemonPersistence {
    private static final Logger LOGGER = Logger.getLogger(PokemonPersistence.class.getName());
    @PersistenceContext(unitName = "pokemonPU")
    protected EntityManager em;
    
    public PokemonEntity create(PokemonEntity pokemonEntity){
        em.persist(pokemonEntity);
        return pokemonEntity;
    }
    
        /**
     * Devuelve las reservas de la base de datos.
     *
     * @return una lista con todas las reservas que encuentre en la base de
     * datos.
     */
    public List<PokemonEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos los Pokemones");
       
        TypedQuery query = em.createQuery("select u from PokemonEntity u", PokemonEntity.class);
        
        return query.getResultList();
    }
     /**
     * Busca si hay alguna reserva con el id que se envía de argumento
     *
     * @param pokeID: id correspondiente a la reserva buscada.
     * @return una reserva.
     */
    public PokemonEntity find(Long pokeID) {
        LOGGER.log(Level.INFO, "Consultando el Pokemon con id={0}", pokeID);
        
        return em.find(PokemonEntity.class, pokeID);
    }
     /**
     * Actualiza un Pokemon.
     *
     * @param pokemonEntity: la reserva que viene con los nuevos cambios.
     * @return una reserva con los cambios aplicados.
     */
    public PokemonEntity update(PokemonEntity pokemonEntity) {
        LOGGER.log(Level.INFO, "Actualizando el author con id={0}", pokemonEntity.getId());
        return em.merge(pokemonEntity);
    }
     /**
     * Borra una reserva de la base de datos recibiendo como argumento el id de
     * la reserva
     *
     * @param pokemonId: id correspondiente a la reserva a borrar.
     */
    public void delete(Long pokemonId) {

        LOGGER.log(Level.INFO, "Borrando el Pokemon con id={0}", pokemonId);
      
        PokemonEntity pokemonEntity = em.find(PokemonEntity.class, pokemonId);
       
        em.remove(pokemonEntity);
    }
    
}
