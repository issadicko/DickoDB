package com.dickodb.utils;

import java.util.ArrayList;
import org.apache.commons.lang3.StringUtils;

public class StringGenerator {

    public static String generate(String token, String separator, int count){
        ArrayList<String> tokens = new ArrayList<>();

        for (int i=0; i<count; i++){
            tokens.add(token);
        }

        return StringUtils.join(tokens, separator);
    }
}
