/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.des.atfsff.common;



/**
 * {field_iter:"id",field_type:"integer",field_content:"123",operator_field:"="}
 *
 * @author martin
 */
public class Condition {

    private String field_iter;
    private String field_type;
    private String field_content;
    private String operator_field;

    public String getField_content() {
        return field_content;
    }

    public void setField_content(String field_content) {
        this.field_content = field_content;
    }

    public String getField_iter() {
        return field_iter;
    }

    public void setField_iter(String field_iter) {
        this.field_iter = field_iter;
    }

    public String getField_type() {
        return field_type;
    }

    public void setField_type(String field_type) {
        this.field_type = field_type;
    }

    public String getOperator_field() {
        return operator_field;
    }

    public void setOperator_field(String operator_field) {
        this.operator_field = operator_field;
    }

    @Override
    public String toString() {
        return "Condition{" + "field_iter=" + field_iter + ", field_type=" + field_type + ", field_content=" + field_content + ", operator_field=" + operator_field + '}';
    }
    
    
    
}
