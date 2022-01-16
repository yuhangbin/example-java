package com.yu.reflect;

import lombok.NoArgsConstructor;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * @author yuhangbin
 * @date 2021/11/26
 **/
public class TypeExample {

    // GenericArrayType represents an array type whose component type is either a parameterized type or a type variable.
    private static GenericArrayType genericArrayType() {
        Generic<Integer>[] generics = new Generic[1];
        Class clazz = generics.getClass();
        return (GenericArrayType) clazz.getGenericSuperclass();
    }
    // ParameterizedType represents a parameterized type such as Collection<String>.

    //WildcardType represents a wildcard type expression, such as ?, ? extends Number, or ? super Integer.

    // TypeVariable is the common superinterface for type variables of kinds.

    public static void main(String[] args) {
       TypeReference typeReference = new TypeReference<Map<Integer,Long>>(){};
        typeReference.print();
    }


    // ParameterizedType Example
    public static class TypeReference<T> {
        public TypeReference() {
        }

        public void print(){
            ParameterizedType superType = (ParameterizedType) this.getClass().getGenericSuperclass();
            Type[] types = superType.getActualTypeArguments();
            printRecursive(types);
        }

        private void printRecursive(Type[] types) {
            for (Type type : types) {
                System.out.println(type.getTypeName());
                if (type instanceof ParameterizedType) {
                    ParameterizedType parameterizedType = (ParameterizedType) type;
                    Type[] types1 = parameterizedType.getActualTypeArguments();
                    printRecursive(types1);
                }
            }
        }
    }

    @NoArgsConstructor
    public static class Generic<T> {
        T data;
    }
}
