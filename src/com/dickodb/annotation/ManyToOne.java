package com.dickodb.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ManyToOne {
    //Classe de a la quelle on est lié
    String target() default "";
    String[] cascade() default {};
    String inversedBy() default "";
    String joinColumn() default "";
}
