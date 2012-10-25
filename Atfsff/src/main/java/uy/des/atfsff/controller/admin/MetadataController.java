/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.des.atfsff.controller.admin;

import java.util.List;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import uy.des.atfsff.conf.ConfigApp;
import uy.des.atfsff.metadata.ent.MEntities;
import uy.des.atfsff.persist.PersistService;

/**
 *
 * @author martin
 */
@Controller
@RequestMapping(value = "/admin/metadata")
public class MetadataController {
    
    @Autowired
    private PersistService persistS;

    @Autowired
    private Validator validator;
    
    @Autowired
    private ConfigApp cfg;     
    
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public String page(){
        return "admin/metadata" ;
    }    
    
    @RequestMapping(method=RequestMethod.GET)
    public @ResponseBody List<MEntities> list(Integer offset) {
        List<MEntities> ret = persistS.getAll(MEntities.class);
        System.out.println("lista obt: "+ret);
        return ret;
    }    
    
}
