package com.dickodb.annotation.descriptors;

public class ManyToOneDescriptor extends RelationDescriptor{
    private String inverseField;
    private String joinColumn;

    public String getInverseField() {
        return inverseField;
    }

    public void setInverseField(String inverseField) {
        this.inverseField = inverseField;
    }

    public String getJoinColumn() {
        return joinColumn;
    }

    public void setJoinColumn(String joinColumn) {
        this.joinColumn = joinColumn;
    }
}
