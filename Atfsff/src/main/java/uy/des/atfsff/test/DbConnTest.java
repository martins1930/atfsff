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
import org.springframework.stereotype.Component;

/**
 *
 * @author martin
 */
@Component
public class DbConnTest {
    
    @Resource(name="dsApp")
    private DataSource dsTest ;
    private JdbcTemplate jdbcTemplate;

    public DbConnTest() {
    }
    
    public String echoDb(){ 
        jdbcTemplate = new JdbcTemplate(dsTest);
        
        List<Posible> posible  = jdbcTemplate.query("select id, nombre from posible", new BeanPropertyRowMapper<Posible>(Posible.class)) ;
        
        Posible primero = posible.get(0);
        
        return primero.toString()+"__total:"+posible.size();
    }
    
    
}
