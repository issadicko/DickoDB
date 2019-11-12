package com.dickodb.annotation.processors;

import com.dickodb.annotation.Table;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TableAnnotationProcessor {

    public static String getTableName(Object object){
        String tablename = "";

        Class<?> cl = object.getClass();
        Table table = cl.getAnnotation(Table.class);

        if (table != null){
            tablename = table.name();
        }
        
        System.out.println("Table : "+tablename);

        return tablename;
    }
    
    public static String getTableName(Class<?> cl){
        String tablename = "";
        Table table = cl.getAnnotation(Table.class);
        
        if (table != null){
            tablename = table.name();
        }

        return tablename;
    }
}
