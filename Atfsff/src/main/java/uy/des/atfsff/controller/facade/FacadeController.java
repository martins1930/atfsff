/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.des.atfsff.controller.facade;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author martin
 */
@Controller
public class FacadeController {

    @RequestMapping("/login")
    public String login() {
        return "/login";
    }
}
