package com.dickodb.persistance;

import com.dickodb.annotation.descriptors.DefaultClassDescriptor;
import com.dickodb.annotation.processors.DefaultProcessor;
import com.dickodb.annotation.processors.TableAnnotationProcessor;
import com.dickodb.querybuilding.Query;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AbstractRepository<T> {
    
    private Class<?> cl;
    private EntityManager em;
    
    public AbstractRepository(Class<?> cl){
        em = EntityManager.getInstance();
        this.cl = cl;
    }
   
    public T find(long id){
        
        String table = TableAnnotationProcessor.getTableName(cl);
        String keyName = DefaultProcessor.getIdName(cl);
        
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        
        Query query  = new Query();
        
        T obj = null;
        
        try {
            
            query.select(table, params);
            ResultSet set = query.execute().getResultSet();
             
            if (set.next()) {
                obj = (T) EntityBuilder.buildOne(set, cl);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AbstractRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return obj;
    }
    
    public ArrayList<T> findAll(){
        ArrayList<T> liste = new ArrayList<>();
        
        String table = TableAnnotationProcessor.getTableName(cl);
        String keyName = DefaultProcessor.getIdName(cl);
        
        Query query = new Query();
        try {
            
            query.select(table, null);
            
            ResultSet set = query.execute().getResultSet();
            liste = (ArrayList<T>) EntityBuilder.buildAll(set, cl);
            
        } catch (SQLException ex) {
            Logger.getLogger(AbstractRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return liste;
    }
    
}
