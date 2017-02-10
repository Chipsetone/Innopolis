package com.semakin.lection5.serializer;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class ReflectionSerializator implements ISerializatorable<String>{
    public String serialize(Object obj) throws IllegalAccessException {
        Class<?> objClass =  obj.getClass();
        String className = objClass.getName();
        Field[] fields = objClass.getDeclaredFields();

        String result = "<object " + getTypeAttribute(className) + ">\n";

        for (Field field :
                fields) {
            String fieldConvert = fieldConvert(field, obj);
            if(fieldConvert.length() > 0) {
                result += fieldConvert + "\n";
            }
        }

        String headerCloseTag = "</object>";
        result += headerCloseTag;

        return result;
    }

    private String fieldConvert(Field field, Object obj) throws IllegalAccessException {
        if(isFieldNotValid(field)){
            return "";
        }
        field.setAccessible(true);

        return "<field " + getTypeAttribute(field.getType().getName()) + " id = \"" + field.getName() + "\" value = \"" + field.get(obj) + "\"/>";
    }

    private boolean isFieldNotValid(Field field){
        int modifiers = field.getModifiers();

        return Modifier.isStatic(modifiers);
    }

    private String getTypeAttribute(String typeName){
        int cutIndexStart = typeName.lastIndexOf(".");
        if(cutIndexStart > 0){
            typeName = typeName.substring(cutIndexStart + 1);
        }

        return "type = \"" + typeName +"\"";
    }
}
