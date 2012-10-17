/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.des.atfsff.controller;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import uy.des.atfsff.test.Contacto;
import uy.des.atfsff.test.DbConnTest;
import uy.des.atfsff.test.Posible;

/**
 *
 * @author martin
 */
@Controller
@RequestMapping(value = "/contact")
public class ContactoController {
    
    @Autowired
    private DbConnTest testDB;

    @Autowired
    private Validator validator;
    
    private static final Logger LOG = Logger.getLogger(ContactoController.class.getName());
    
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public String page(){
        return "contacto" ;
    }

    @RequestMapping(value = "/list",  method=RequestMethod.GET)
    public @ResponseBody List<Contacto> list() {
        LinkedList<Contacto> ret = new LinkedList<>();
        ret.add(new Contacto("martin22t", new Date(), "mensaje 1", 25));
        ret.add(new Contacto("martin22", new Date(), "mensaje 2", 26));
        return ret;
    }
    
    @RequestMapping(value = "/add",  method=RequestMethod.POST)
    public @ResponseBody String add(@RequestBody Contacto contacto) {
        System.out.println("add init jackson");
        LOG.log(Level.INFO, "{0}",contacto);
        return contacto.getNombre()+"---dbTest: "+testDB.echoDb();
    }    
    
    @RequestMapping(value = "/addValid",  method=RequestMethod.POST)
    public @ResponseBody String addValid(@RequestBody Contacto contacto) {
        StringBuilder sb = new StringBuilder();
        
        System.out.println("addValid init jackson");
        System.out.println(""+contacto);
        Set<ConstraintViolation<Contacto>> validateC = validator.validate(contacto);
        for (ConstraintViolation<Contacto> constraintViolation : validateC) {
            

            
            sb.append(constraintViolation.getMessage())
              .append(":")
              .append(constraintViolation.getInvalidValue().toString())
              .append(":")      
              .append("valor prop=>")      
              .append(constraintViolation.getPropertyPath().toString())      
              .append(":fin:map:");
            

        }
        Posible p = new Posible();
        p.setId(23);
        p.setNombre("hola"+System.currentTimeMillis());
        testDB.updDb(p);
        return sb.toString();
    }    
    

}
