package com.plus.util;

import java.lang.reflect.Method;

/**
 * @author plus me
 * @date 2018/7/2
 */
public abstract class ObjectUtils {
    public static Boolean isObjectMethod(Method method){
        Method[] ms = Object.class.getMethods();
        for(Method m : ms){
            if(method.toGenericString().equals(m.toGenericString().toString())){
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    public static  void  main(String[] args){
        //isObjectMethod(null);

    }
}
