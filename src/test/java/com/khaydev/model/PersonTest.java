package com.khaydev.model;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static junit.framework.Assert.assertTrue;

class PersonTest {

    @Test
    void givenObject_whenGetsFieldNamesAtRuntime_thenCorrect(){
        Object person = new Person();
        Field[] fields = person.getClass().getDeclaredFields();
        Set<String> fieldNames = getFieldNames(fields);

        assertTrue(Set.of("name", "age").containsAll(fieldNames));
    }

    private static Set<String> getFieldNames(Field[] fields){
        return Arrays.stream(fields).map(Field::getName).collect(Collectors.toSet());
    }
}
