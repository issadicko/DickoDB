package com.dickodb.exception;

import java.lang.reflect.Field;

public class BadTargetException  extends Exception{
    private String target = "";
    private Class<?> classe;
    private Field field;

    public BadTargetException(String target, Class<?> cl, Field field){
        this.target = target;
        this.classe = cl;
        this.field = field;
    }

    @Override
    public String getMessage() {
        return "\n|====================== WOODI - Exception =======================| " +
                "\n\tAucune classe ne correspond au target : '"+target+"' que vous avez defini\n"+
                         "\t\tCLASS : "+classe.getSimpleName()+
                         "\n\t\tCHAMP : "+field.getName()+
                "\n================================================================|";
    }
}
