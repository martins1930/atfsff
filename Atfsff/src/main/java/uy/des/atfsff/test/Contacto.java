/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.des.atfsff.test;

import java.util.Date;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;



/**
 *
 * @author martin
 */
public class Contacto {

    @NotEmpty
    private String nombre;
    
    @Range(min = 1, max = 150)
    private Integer edad ;
    
    private Date fec_contact;
    private String msg;

    public Contacto() {
    }

    public Contacto(String nombre, Date fec_contact, String msg, Integer e) {
        this.nombre = nombre;
        this.fec_contact = fec_contact;
        this.msg = msg;
        this.edad = e ;
    }
    
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFec_contact() {
        return fec_contact;
    }

    public void setFec_contact(Date fec_contact) {
        this.fec_contact = fec_contact;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }
    
    

    @Override
    public String toString() {
        return "Contacto{" + "nombre=" + nombre + ", fec_contact=" + fec_contact + ", msg=" + msg + '}';
    }
    
    
    
}
