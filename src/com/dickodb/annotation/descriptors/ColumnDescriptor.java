package com.dickodb.annotation.descriptors;

public class ColumnDescriptor extends AbstractDescriptor{
    private boolean nullable;

    public boolean isNullable() {
        return nullable;
    }

    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }
}
