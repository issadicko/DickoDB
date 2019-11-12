/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dickodb.persistance;

import com.dickodb.annotation.descriptors.ClassDescriptionLoader;
import com.dickodb.annotation.descriptors.ColumnDescriptor;
import com.dickodb.annotation.descriptors.DefaultClassDescriptor;
import com.dickodb.annotation.descriptors.KeyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author genius
 */
public class EntityBuilder {
    
    
    private static DefaultClassDescriptor classDescriptor;
    
    public static Object buildOne(ResultSet resultSet, Class<?> cl){
        Object obj = null;
        
        try {
            
               obj = cl.getConstructor().newInstance();
               
               classDescriptor = ClassDescriptionLoader.describe(obj);
               
               for (ColumnDescriptor col : classDescriptor.getSimpleColumns()) {
                   Field field = cl.getDeclaredField(col.getFieldName());
                   field.setAccessible(true);
                   field.set(obj, resultSet.getObject(col.getName()));
               }
               
               
               for (KeyDescriptor col : classDescriptor.getKeys()) {
                   Field field = cl.getDeclaredField(col.getFieldName());
                   field.setAccessible(true);
                   field.set(obj, resultSet.getObject(col.getName()));
               }
               
        } catch (NoSuchMethodException      | 
                SecurityException           | 
                InstantiationException      | 
                IllegalAccessException      | 
                IllegalArgumentException    | 
                InvocationTargetException   |
                NoSuchFieldException        |
                SQLException ex) {
            Logger.getLogger(EntityBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return obj;
    }
    
    
    public static ArrayList<Object> buildAll(ResultSet set, Class<?> cl){
        ArrayList<Object> liste = new ArrayList<>();
        
        try {
            
            while (set.next()) {
                
                liste.add(buildOne(set, cl));
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EntityBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return liste;
    }
    
}
