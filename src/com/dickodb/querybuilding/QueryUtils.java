package com.dickodb.querybuilding;

import com.dickodb.utils.StringGenerator;
import java.util.HashMap;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;


public class QueryUtils {

    public static String getCreateQuery(Set<String> fields, String tablename){
        StringBuilder query = new StringBuilder();

        query.append("INSERT INTO ").append(tablename).append("(")
                .append(StringUtils.join(fields, ", "))
                .append(") VALUES (")
                .append(StringGenerator.generate("?", ", ", fields.size()))
                .append(")");

        return query.toString();
    }
    
    public static String getSelectQuery(String tableName){
        return  "SELECT * FROM "+tableName;
    }
    
    public static String createWhereClause(Set<String> map){
        StringBuilder query = new StringBuilder();
        
        query.append("WHERE ")
             .append(StringUtils.join(map, " = ?,"));
        
        return query.toString()+" = ?";
    }

    static String createDeleteQuery(String table) {
        return "DELETE FROM "+table;
    }
}
