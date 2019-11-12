package com.dickodb.annotation.descriptors;

abstract class AbstractDescriptor {
    protected String name;
    protected Object value;
    protected String fieldName;


    public String getName() {
       return (name.isEmpty()) ? fieldName : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
}
