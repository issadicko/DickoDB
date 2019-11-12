package com.dickodb.annotation.descriptors;

public class RelationDescriptor extends AbstractDescriptor{
    private Class<?> target;
    private String[] cascade;

    public Class<?> getTarget() {
        return target;
    }

    public void setTarget(Class<?> target) {
        this.target = target;
    }

    public String[] getCascade() {
        return cascade;
    }

    public void setCascade(String[] cascade) {
        this.cascade = cascade;
    }

    public boolean isPersistCascade(){

        for(String str: getCascade()){
            if (str.equals("persist")){
                return true;
            }
        }

        return false;
    }
}
