/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.des.atfsff.persist;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ClassUtils;
import uy.des.atfsff.common.Condition;

/**
 *
 * @author martin
 */
@Component
@Transactional(readOnly=true)
@Secured("ROLE_SPITTER")
public class PersistService {
    
    private static final Logger log = Logger.getLogger(PersistService.class.getName());
    
    @PersistenceContext
    private EntityManager em ; 
    
    
    
    public <T> void save(T t){
        log.log(Level.FINE, "Pesristed element: {0}", t);
        em.persist(t);
    }    
    
    public <T> List<T> getAll(Class<T> clazz){
        System.out.println("Clase obtenida "+clazz.getName());
        String nombreEntidad = ClassUtils.getShortName(clazz) ;
        TypedQuery<T> query = em.createQuery("select t from "+nombreEntidad+" t", clazz);
        return query.getResultList();
    }    
    
    public <T> List<T> getAll(Class<T> clazz, List<Condition> condition, Integer startPos, Integer maxResult){   
        TypedQuery<T> query ;
        String nombreEntidad = ClassUtils.getShortName(clazz) ;        
        if (condition!=null) {
            query = em.createQuery("select t from "+nombreEntidad+" t where "+condition, clazz)
                      .setFirstResult(startPos)
                      .setMaxResults(maxResult);
        }
        else{ 
            log.log(Level.FINE, "The condition is empty.");
            query = em.createQuery("select t from "+nombreEntidad+" t", clazz)
                      .setFirstResult(startPos)
                      .setMaxResults(maxResult);                    
        }
        return query.getResultList();
    }    
    
    
}
