
package uy.des.atfsff.controller ;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class TestController {

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody  String prueba(){
		return "HOLA SPRING MVC!! DIRECCION ARCHIVOGGGG--!!!!" ;	 
	}

	@RequestMapping(value = "/list2", method = RequestMethod.GET)
	public @ResponseBody  String prueba2(){
		return "HOLA SPRING MVC!! DIRECCION ARCHIVO22ssss!!!!" ;	
	}
        
        
}

