/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.des.atfsff.controller;

import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import uy.des.atfsff.conf.ConfigApp;
import uy.des.atfsff.persist.PersistService;
import uy.des.atfsff.test.DbConnTest;
import uy.des.atfsff.test.ent.PosibleEnt;

/**
 *
 * @author martin
 */
@Controller
@RequestMapping(value = "/posible")
public class PosibleController {
    
    @Autowired
    private PersistService persistS;

    @Autowired
    private Validator validator;
    
    @Autowired
    private ConfigApp cfg; 
    
    
    
}
