/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.des.atfsff.metadata.ent;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

/**
 *
 * @author martin
 */
@Entity
@Table(name="mentities")
public class MEntities implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)    
    private Long id;
    
    @Column(length=500, nullable=false, unique=true)
    private String className;
    
    @Column(length=500, nullable=false)
    private String default_order_by;
    
    @Column(length=1, nullable=false)
    private String is_view;
    
    @ElementCollection(fetch=FetchType.EAGER)
    @CollectionTable(name = "mfield", joinColumns = @JoinColumn(name = "mentities_id"))
    @OrderColumn(name="f_order")
    private List<MField> fields;

    public MEntities() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getDefault_order_by() {
        return default_order_by;
    }

    public void setDefault_order_by(String default_order_by) {
        this.default_order_by = default_order_by;
    }

    public String getIs_view() {
        return is_view;
    }

    public void setIs_view(String is_view) {
        this.is_view = is_view;
    }

    public List<MField> getFields() {
        return fields;
    }

    public void setFields(List<MField> fields) {
        this.fields = fields;
    }

    @Override
    public String toString() {
        return "MEntities{" + "id=" + id + ", className=" + className + ", default_order_by=" + default_order_by + ", is_view=" + is_view + ", fields=" + fields + '}';
    }


    
    
}
