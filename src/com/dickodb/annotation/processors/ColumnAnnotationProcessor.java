package com.dickodb.annotation.processors;

import com.dickodb.annotation.Column;
import com.dickodb.annotation.descriptors.ColumnDescriptor;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class ColumnAnnotationProcessor {

    public static ArrayList<ColumnDescriptor> getColumns(Object object)
    {
        ArrayList<ColumnDescriptor> columns = new ArrayList<>();

        try {

            Class<?> cl = object.getClass();

            for (Field f: cl.getDeclaredFields())
            {
                Column column = f.getAnnotation(Column.class);
                if (column != null)
                {
                    ColumnDescriptor descriptor = new ColumnDescriptor();
                    descriptor.setNullable(column.nullable());
                    descriptor.setName(column.name());
                    descriptor.setFieldName(f.getName());
                    columns.add(descriptor);
                }
            }

        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        return columns;
    }

}
