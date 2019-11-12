package com.dickodb.querybuilding;

import com.dickodb.annotation.descriptors.DefaultClassDescriptor;
import com.dickodb.annotation.descriptors.ClassDescriptionLoader;
import com.dickodb.persistance.EntityManager;

import java.sql.*;
import java.util.Map;

public class Query {

    private PreparedStatement statement;
    private ResultSet resultSet;

    /**
     * Prepare l'insertion de l'objet dans la base de données
     *
     * @param object
     * @param connection
     * @return Query
     * @throws SQLException
     */
    public Query prepareInsertion(Object object, Connection connection) throws SQLException {

        DefaultClassDescriptor descriptor = ClassDescriptionLoader.describe(object);

        Map<String, Object> fields = descriptor.getPersistFields(object);

        String query = QueryUtils.getCreateQuery(fields.keySet(), descriptor.getTable());

        this.statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        bindParams(fields);

        return this;
    }

    public Query select(String tableName, Map<String, Object> whereMap) throws SQLException {

        String query = QueryUtils.getSelectQuery(tableName);

        if (whereMap != null) {

            query = query + " " + QueryUtils.createWhereClause(whereMap.keySet());

        }

        this.statement = EntityManager.getInstance().getConnection().prepareStatement(query);

        if (whereMap != null) {
            bindParams(whereMap);
        }

        return this;
    }

    public Query execute() throws SQLException {
        this.statement.execute();
        return this;
    }

    public ResultSet getResultSet() throws SQLException {
        return statement.getResultSet();
    }

    // Methode privé
    /**
     * Insert les valeurs des paramètre dans la requête
     *
     * @param values liste des valeurs
     * @throws SQLException
     */
    private void bindParams(Map<String, Object> values) throws SQLException {
        int i = 1;

        for (String key : values.keySet()) {
            statement.setObject(i, values.get(key));
            i++;
        }

    }

    /**
     * retourne le statement actuel de la requête
     *
     * @return statement
     */
    public PreparedStatement getStatement() {
        return this.statement;
    }

    public Query delete(String table, Map<String, Object> map) throws SQLException{

        String query = QueryUtils.createDeleteQuery(table);
        if (map != null) {
            query = query + " " + QueryUtils.createWhereClause(map.keySet());
        }
        
        this.statement = EntityManager.getInstance().getConnection().prepareStatement(query);

        if (map != null) {
            bindParams(map);
        }

        return this;
    }

}
