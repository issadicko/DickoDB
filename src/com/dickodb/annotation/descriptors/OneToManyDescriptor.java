package com.dickodb.annotation.descriptors;

public class OneToManyDescriptor extends RelationDescriptor{

    private String mapField;


    public String getMapField() {
        return mapField;
    }

    public void setMapField(String mapField) {
        this.mapField = mapField;
    }


}
