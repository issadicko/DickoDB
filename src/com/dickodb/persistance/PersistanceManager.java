package com.dickodb.persistance;

import com.dickodb.annotation.descriptors.ClassDescriptionLoader;
import com.dickodb.annotation.descriptors.DefaultClassDescriptor;
import com.dickodb.annotation.descriptors.KeyDescriptor;
import com.dickodb.annotation.processors.DefaultProcessor;
import com.dickodb.annotation.processors.TableAnnotationProcessor;
import com.dickodb.querybuilding.Query;
import com.woodi.orm.exception.NoPrimaryKeyException;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

class PersistanceManager {

    private Query query;
    private Connection connection;

    PersistanceManager(Connection connection) {
        this.connection = connection;
        query = new Query();
    }

    void insertObject(Object object) throws NoPrimaryKeyException, SQLException {

        ArrayList<KeyDescriptor> keysList = ClassDescriptionLoader.describe(object).getKeys();

        if (keysList.isEmpty()) {
            throw new NoPrimaryKeyException("Vous n'avez pas defini de clé primaire pour la classe : " + object.getClass());
        }

        query.prepareInsertion(object, this.connection);
        ResultSet set = query
                .execute()
                .getStatement()
                .getGeneratedKeys();

        try {
            if (set.next()) {
                Object key = set.getObject(1);
                KeyDescriptor key_desc = keysList.get(0);
                Field field = object.getClass().getDeclaredField(key_desc.getFieldName());
                field.setAccessible(true);
                field.set(object, key);
            }
        } catch (NoSuchFieldException | IllegalAccessException | IllegalArgumentException ex) {
            ex.printStackTrace();
        }

    }

    void delete(Object object) {
        
        DefaultClassDescriptor descriptor = ClassDescriptionLoader.describe(object);
        String table = descriptor.getTable();
        
        KeyDescriptor kd = descriptor.getKeys().get(0);
        
        Query query = new Query();
        Map<String, Object> map = new HashMap();
        
        try {
            
            Field keyField = object.getClass().getDeclaredField(kd.getFieldName());
            keyField.setAccessible(true);
            map.put(kd.getName(), keyField.get(object));
        
            query.delete(table, map).execute();
            
        } catch (NoSuchFieldException | SecurityException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(PersistanceManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
}
