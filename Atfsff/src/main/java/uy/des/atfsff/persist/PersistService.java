/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.des.atfsff.persist;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
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
public class PersistService<T extends Serializable> {
    
    private static final Logger log = Logger.getLogger(PersistService.class.getName());
    
    @PersistenceContext
    private EntityManager em ;
    
    //metodos para obtener la entidad
    public Class<T> getEntityClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
    
    protected T getNewInstance(){
        Class<T> clazzT = getEntityClass();
        T ret = null ;
        try {
            ret = clazzT.newInstance(); 
        } catch (InstantiationException ex) {
            log.log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            log.log(Level.SEVERE, null, ex);
        }
        return ret ;
    }    
    
    
    public void save(T t){
        log.log(Level.FINE, "Pesristed element: {0}", t);
        em.persist(t);
    }    
    
    public List<T> getAll(){
        Class<T> clazz = this.getEntityClass();
        String nombreEntidad = ClassUtils.getShortName(clazz) ;
        TypedQuery<T> query = em.createQuery("select t from "+nombreEntidad+" t", clazz);
        return query.getResultList();
    }    
    
    public List<T> getAll(List<Condition> condition, Integer startPos, Integer maxResult){
        Class<T> clazz = this.getEntityClass();        
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
