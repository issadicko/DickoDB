package com.dickodb.annotation.resolvers;

import com.dickodb.annotation.descriptors.ClassDescriptionLoader;
import com.dickodb.annotation.descriptors.ManyToOneDescriptor;
import java.lang.reflect.Field;

public class RelationResolver {
    public static Object resolve(ManyToOneDescriptor mto, Object object) throws NoSuchFieldException, IllegalAccessException, ArrayIndexOutOfBoundsException{

        Class<?> cl = object.getClass();

        //Recuperation de l'objet mappeur
        Field mapper = cl.getDeclaredField(mto.getFieldName());
        mapper.setAccessible(true);
        Object map_object = mapper.get(object);

        String mapper_keyFieldName = ClassDescriptionLoader.describe(map_object).getKeys().get(0).getFieldName();
        Field mapper_kf = map_object.getClass().getDeclaredField(mapper_keyFieldName);
        mapper_kf.setAccessible(true);

        return mapper_kf.get(map_object);
    }
}
