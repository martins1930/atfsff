/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.des.atfsff.test;

import java.util.LinkedList;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.sql.DataSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import uy.des.atfsff.metadata.ent.MEntities;
import uy.des.atfsff.metadata.ent.MField;
import uy.des.atfsff.test.ent.PosibleEnt;

/**
 *
 * @author martin
 */
@Component
@Transactional(readOnly=true)
@Secured("ROLE_SPITTER")
public class DbConnTest {
    // ver de hacer esto con una interface!!
    
    @Resource(name="dsApp")
    private DataSource dsTest ;

//    @PersistenceContext(unitName="Atfsff-PU")    
    @PersistenceContext
    private EntityManager em ;
    
    private JdbcTemplate jdbcTemplate;

    public DbConnTest() {
    }
    
    @Secured("ROLE_ADMIN")
    public String echoDb(){ 
        if (jdbcTemplate==null) {
            jdbcTemplate = new JdbcTemplate(dsTest);    
        }
        
        
        List<Posible> posible  = jdbcTemplate.query("select id, nombre from posible", new BeanPropertyRowMapper<Posible>(Posible.class)) ;
        
        Posible primero = posible.get(0);
        
        return primero.toString()+"__total:"+posible.size();
    }
    
    @Transactional(propagation=Propagation.REQUIRED)
    public String updDb(Posible cupd){
        if (jdbcTemplate==null) {
            jdbcTemplate = new JdbcTemplate(dsTest);    
        }
        
        String sql = "UPDATE posible set nombre = ? where id = ?"; 
                
        jdbcTemplate.update(sql, new Object[] { cupd.getNombre(),
                cupd.getId()
        });                
        return "update succ" ;
    }
    
    @Transactional(propagation=Propagation.REQUIRED)
    public String updDbJPA(Posible cupd){
        PosibleEnt pObt = em.find(PosibleEnt.class, cupd.getId());
        pObt.setNombre(cupd.getNombre());
        
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<PosibleEnt> cquery = criteriaBuilder.createQuery(PosibleEnt.class);
        // cquery.from
        Root<PosibleEnt> rent = cquery.from(PosibleEnt.class);
        
        LinkedList<Predicate> ll = new LinkedList<>();

        //build cond
        ll.add( criteriaBuilder.isNotNull(rent.get("id")) );
        ll.add( criteriaBuilder.like( rent.<String>get("nombre"), "hola%") );
        
        Predicate[] lpred = new Predicate[ll.size()];
        
        //cquery.where 
        cquery.where(ll.toArray(lpred));
        TypedQuery<PosibleEnt> result = em.createQuery(cquery);
        
        MEntities mEntities = new MEntities();
        mEntities.setClassName("uy.des.atfsff.test.ent.PosibleEnt");
        mEntities.setDefault_order_by("nombre");
        mEntities.setIs_view("N");
        LinkedList<MField> asocEnt = new LinkedList<>();
        MField mField1 = new MField();
        MField mField2 = new MField();
        asocEnt.add(mField2);        
        asocEnt.add(mField1);
        mEntities.setFields(asocEnt);
        
        mField1.setField_name("id");
        mField1.setField_type("Integer");
        mField1.setLarge("N");
        
        mField2.setField_name("nombre");
        mField2.setField_type("String");
        mField2.setLarge("N");
        
        em.persist(mEntities);
        
        
        
        return "update succ "+result.getResultList().size() ;
    }
    
}
