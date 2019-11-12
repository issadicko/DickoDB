package com.dickodb.persistance;

import com.dickodb.dbconnectivity.DBConnection;
import com.woodi.orm.exception.NoPrimaryKeyException;

import java.sql.Connection;
import java.sql.SQLException;

public class EntityManager {
    private Connection connection;
    PersistanceManager manager;
    
    private static EntityManager em;

    private EntityManager(){
        connection = DBConnection.getConnection();
        manager = new PersistanceManager(connection);
    }

    public void persist(Object object) {

        try {

            manager.insertObject(object);

        } catch (NoPrimaryKeyException | SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }
    
    public void remove(Object obj){
        manager.delete(obj);
    }

    public void flush(){
        try {
            connection.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    
    public static EntityManager getInstance(){
        if (em == null) {
            em = new EntityManager();
        }
        
        return em;
    }
    
}
