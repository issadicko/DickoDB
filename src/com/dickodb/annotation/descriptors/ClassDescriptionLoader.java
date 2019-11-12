package com.dickodb.annotation.descriptors;

import com.dickodb.annotation.processors.DefaultProcessor;

import java.util.HashMap;
import java.util.Map;

public class ClassDescriptionLoader {
    private static Map<String, DefaultClassDescriptor> descriptorMap;

    private ClassDescriptionLoader(){
        descriptorMap = new HashMap<>();
    }

    public static DefaultClassDescriptor describe(Object object){
        if (descriptorMap == null) new ClassDescriptionLoader();

        DefaultClassDescriptor desc = descriptorMap.getOrDefault(object.getClass().getName(), null);

        if (desc != null) return desc;

        return loadClassDescription(object);
    }

    private static DefaultClassDescriptor loadClassDescription(Object obj) {
        DefaultClassDescriptor desc = DefaultProcessor.process(obj);
        descriptorMap.put(obj.getClass().getName(), desc);

        return desc;
    }
}
