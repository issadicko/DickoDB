package com.dickodb.annotation.processors;

import com.dickodb.annotation.ManyToOne;
import com.dickodb.annotation.OneToMany;
import com.dickodb.annotation.descriptors.DefaultClassDescriptor;
import com.dickodb.annotation.descriptors.KeyDescriptor;
import com.dickodb.annotation.descriptors.ManyToOneDescriptor;
import com.dickodb.annotation.descriptors.OneToManyDescriptor;
import com.dickodb.exception.BadTargetException;

import java.lang.reflect.Field;
import java.util.ArrayList;
import com.dickodb.annotation.Id;

public class DefaultProcessor {

    public static DefaultClassDescriptor process(Object obj){
        DefaultClassDescriptor desc = new DefaultClassDescriptor();
        desc.setTable(TableAnnotationProcessor.getTableName(obj));

        desc.setSimpleColumns(ColumnAnnotationProcessor.getColumns(obj));
        desc.setKeys(getClassKeys(obj.getClass()));

        try {
            desc.setOnetomanys(getOtmRelations(obj.getClass()));
            desc.setManytoones(getMtoRelations(obj.getClass()));
        }catch (BadTargetException ex){
            System.out.println(ex.getMessage());
            System.exit(0);
        }

        return desc;
    }

    private static ArrayList<KeyDescriptor> getClassKeys(Class<?> cl){
        ArrayList<KeyDescriptor> keys = new ArrayList<>();

        try{

            for (Field field: cl.getDeclaredFields()){
                Id primaryKey = field.getAnnotation(Id.class);
                if (primaryKey != null){
                    KeyDescriptor descriptor = new KeyDescriptor();
                    descriptor.setAutoGenerated(primaryKey.autoGenerated());
                    descriptor.setName(primaryKey.name());
                    descriptor.setFieldName(field.getName());

                    keys.add(descriptor);
                }
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }

        return keys;
    }
    
    
    public static String getIdName(Class<?> cl){
        
        String name = null;
        
        for (Field field : cl.getDeclaredFields()) {
            
            Id key = field.getAnnotation(Id.class);
            if (key != null) {
                    name = (key.name().isEmpty()) ? field.getName() : key.name();
            }
            
        }
        
        return name;
        
    }
    

    /**
     * Charge tout les champs de relation du type <strong>OneToMany</strong>
     * @param cl classe dont on doit charger les relation <strong>OneToMany</strong>
     * @return ArrayList la liste de relation
     * @throws BadTargetException Une mauvaise un target qui ne correspond a aucune classe
     */
    private static ArrayList<OneToManyDescriptor> getOtmRelations(Class<?> cl) throws BadTargetException{
        ArrayList<OneToManyDescriptor> descriptors = new ArrayList<>();


        for (Field field: cl.getDeclaredFields()){
            OneToMany oneToMany = field.getAnnotation(OneToMany.class);
            if (oneToMany != null){
                OneToManyDescriptor descriptor = new OneToManyDescriptor();
                descriptor.setCascade(oneToMany.cascade());
                descriptor.setMapField(oneToMany.mappedBy());
                descriptor.setFieldName(field.getName());

                try {
                    descriptor.setTarget(Class.forName(oneToMany.target()));
                } catch (ClassNotFoundException e) {
                    throw new BadTargetException(oneToMany.target(), cl, field);
                }

                descriptors.add(descriptor);

            }
        }

        return descriptors;
    }


    /**
     * Charge tout les champs de relation du type <strong>ManyToOne</strong>
     * @param cl classe dont on doit charger les relation <strong>ManyToOne</strong>
     * @return ArrayList la liste de relation
     * @throws BadTargetException Une mauvaise un target qui ne correspond a aucune classe
     */
    private static ArrayList<ManyToOneDescriptor> getMtoRelations(Class<?> cl) throws BadTargetException{
        ArrayList<ManyToOneDescriptor> descriptors = new ArrayList<>();

        for (Field field: cl.getDeclaredFields()){
            ManyToOne manyToOne = field.getAnnotation(ManyToOne.class);
            if (manyToOne != null){
                
                ManyToOneDescriptor descriptor = new ManyToOneDescriptor();
                descriptor.setCascade(manyToOne.cascade());
                descriptor.setInverseField(manyToOne.inversedBy());
                descriptor.setJoinColumn(manyToOne.joinColumn());
                descriptor.setFieldName(field.getName());

                try {
                    descriptor.setTarget(Class.forName(manyToOne.target()));
                } catch (ClassNotFoundException e) {
                    throw new BadTargetException(manyToOne.target(), cl, field);
                }

                descriptors.add(descriptor);

            }

        }

        return descriptors;
    }

}