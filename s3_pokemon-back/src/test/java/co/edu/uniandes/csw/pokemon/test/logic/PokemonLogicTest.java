/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pokemon.test.logic;

import co.edu.uniandes.csw.pokemon.constants.PokemonType;
import co.edu.uniandes.csw.pokemon.ejb.PokemonLogic;
import co.edu.uniandes.csw.pokemon.entities.PokemonEntity;
import co.edu.uniandes.csw.pokemon.entities.ataqueEntity;
import co.edu.uniandes.csw.pokemon.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.pokemon.persistence.PokemonPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author DAniel Angel
 */
@RunWith(Arquillian.class)
public class PokemonLogicTest {
    
    @PersistenceContext
    private EntityManager em;

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private PokemonLogic pokemonLogic;

    @Inject
    private UserTransaction utx;

    private List<PokemonEntity> data = new ArrayList<>();

    @Deployment
    public static JavaArchive CreateDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PokemonEntity.class.getPackage())
                .addPackage(PokemonPersistence.class.getPackage())
                .addPackage(PokemonLogic.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    @Before
    public void configTest() {
        try {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete from ataqueEntity").executeUpdate();
        em.createQuery("delete from PokemonEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            PokemonEntity entity = factory.manufacturePojo(PokemonEntity.class);

            em.persist(entity);
            data.add(entity);
        }
        ataqueEntity plan = factory.manufacturePojo(ataqueEntity.class);
        em.persist(plan);
        data.get(1).getAtaques().add(plan);
       
    }

    /**
     *
     * @throws BusinessLogicException
     */
    @Test
    public void createPokemon() throws BusinessLogicException {
        PokemonEntity newEntity = factory.manufacturePojo(PokemonEntity.class);
        newEntity.setPokeID(Long.MIN_VALUE);
        newEntity.setAltura(12.12);
        ataqueEntity ataque = new ataqueEntity();
        ataque.setDanio(20);
        ataque.setId(Long.MIN_VALUE);
        ataque.setNombre("bola de fuego");
        ArrayList<ataqueEntity> ataques = new ArrayList<>();
        ataques.add(ataque);
        newEntity.setAtaques(ataques);
        newEntity.setDescripcion(" hola");
        newEntity.setNombre("Charizard");
        newEntity.setPeso(12.4);
        
        newEntity.setPokeDebilidad(PokemonType.ELECTRIC);
        newEntity.setPokeType(PokemonType.FIRE);
        PokemonEntity result = new PokemonEntity();
      
        result = pokemonLogic.createPokemon(newEntity);
        Assert.assertNotNull(result);
        
        PokemonEntity entity = em.find(PokemonEntity.class, result.getId());
        Assert.assertEquals(entity.getId(), result.getId());
        Assert.assertEquals(entity.getNombre(), result.getNombre());
    }

 

    @Test(expected = BusinessLogicException.class)
    public void createPokemonFake() throws BusinessLogicException {
        PokemonEntity newEntity = factory.manufacturePojo(PokemonEntity.class);
        newEntity.setAltura(12.12);
        ArrayList ataques = new ArrayList<ataqueEntity>();
        ataqueEntity a1 = new ataqueEntity();
        a1.setDanio(1);
        ataques.add(a1);
        newEntity.setAtaques(ataques);
        newEntity.setDescripcion(" hola");
        newEntity.setNombre("Charizard");
        newEntity.setPeso(12.4);
        newEntity.setPokeID(Long.MIN_VALUE);
        PokemonEntity result = pokemonLogic.createPokemon(newEntity);
        Assert.assertNotNull(result);
        
        PokemonEntity entity = em.find(PokemonEntity.class, result.getId());
        Assert.assertEquals(entity.getId(), result.getId());
        Assert.assertEquals(entity.getNombre(), result.getNombre());
    }

    
}
