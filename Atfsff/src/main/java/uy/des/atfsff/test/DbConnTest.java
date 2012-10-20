/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.des.atfsff.test;

import java.util.List;
import javax.annotation.Resource;
import javax.sql.DataSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author martin
 */
@Component
@Transactional(readOnly=true)
@Secured("ROLE_SPITTER")
public class DbConnTest {
    
    @Resource(name="dsApp")
    private DataSource dsTest ;
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
    
}
