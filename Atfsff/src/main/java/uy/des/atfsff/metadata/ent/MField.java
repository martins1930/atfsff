/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.des.atfsff.metadata.ent;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;

/**
 *
 * @author martin
 */
@Embeddable
@Table(name="mfield")
public class MField implements Serializable{
    
    @Column(length=255, nullable=false)    
    private String field_name;
    
    @Column(length=255, nullable=false)    
    private String field_type;
    
    @Column(length=1, nullable=false)    
    private String large;
    
    public MField() {
    }

    public String getField_name() {
        return field_name;
    }

    public void setField_name(String field_name) {
        this.field_name = field_name;
    }

    public String getField_type() {
        return field_type;
    }

    public void setField_type(String field_type) {
        this.field_type = field_type;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    @Override
    public String toString() {
        return "MField{" + "field_name=" + field_name + ", field_type=" + field_type + ", large=" + large + '}';
    }
    
    

    
}
