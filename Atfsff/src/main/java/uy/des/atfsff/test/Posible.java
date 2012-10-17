/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.des.atfsff.test;

/**
 *
 * @author martin
 */
public class Posible {
    
    private Integer id ;
    private String nombre;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Posible{" + "id=" + id + ", nombre=" + nombre + '}';
    }
    
    
    
}
