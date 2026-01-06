package com.tek271.jpop.prompt;

import java.lang.annotation.*;


/**
 * When this annotation is used on a method definition, the return type is a Json string.
 * When this annotation is used on a method parameter, the parameter is expected to be a Json String
 */
@Documented
@Target( {ElementType.METHOD, ElementType.PARAMETER} )
@Retention( RetentionPolicy.RUNTIME )
public @interface IsJson {
}
