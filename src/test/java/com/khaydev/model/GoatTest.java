package com.khaydev.model;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class GoatTest {

    @Test
    @Order(1)
    void testGetClassName(){
        Object goat = new Goat();
        Class<?> clazz = goat.getClass();

        assertEquals("Goat", clazz.getSimpleName());
        assertEquals("com.khaydev.model.Goat", clazz.getName());
        assertEquals("com.khaydev.model.Goat", clazz.getCanonicalName());
    }

    @Test
    @Order(2)
    void testShouldCreateClassWhenFullyQualifiedClassNameIsProvided() throws ClassNotFoundException {
        Class<?> clazz = Class.forName("com.khaydev.model.Goat");

        assertEquals("Goat", clazz.getSimpleName());
        assertEquals("com.khaydev.model.Goat", clazz.getName());
        assertEquals("com.khaydev.model.Goat", clazz.getCanonicalName());
    }

    @Test
    @Order(3)
    void testShouldVerifyModifiers() throws ClassNotFoundException {
        Class<?> goatClass = Class.forName("com.khaydev.model.Goat");
        Class<?> animalClass = Class.forName("com.khaydev.model.Animal");

        int goatModifiers = goatClass.getModifiers();
        int animalModifiers = animalClass.getModifiers();

        assertTrue(Modifier.isPublic(goatModifiers));
        assertTrue(Modifier.isAbstract(animalModifiers));
        assertTrue(Modifier.isPublic(animalModifiers));
    }

    @Test
    @Order(4)
    void testShouldVerifyPackageInfo() {
        Object goat = new Goat();
        Class<?> goatClass = goat.getClass();
        Package goatPackage = goatClass.getPackage();

        assertEquals("com.khaydev.model", goatPackage.getName());
    }

    @Test
    @Order(5)
    void testShouldGetSuperClassInfo(){
        Object goat = new Goat();
        Class<?> goatClass = goat.getClass();
        Class<?> superclass = goatClass.getSuperclass();

        assertEquals("Animal", superclass.getSimpleName());
    }

    @Test
    @Order(6)
    void testShouldGetImplementedInterfaces(){
        Object goat = new Goat();
        Class<?> goatClass = goat.getClass();
        Class<?>[] interfaces = goatClass.getInterfaces();

        assertEquals(1, interfaces.length);
        assertEquals("Locomotion", interfaces[0].getSimpleName());
    }

    @Test
    @Order(7)
    void testShouldVerifyConstructorInfo(){
        Object goat = new Goat();
        Class<?> goatClass = goat.getClass();
        Constructor<?>[] constructors = goatClass.getConstructors();

        assertEquals(1, constructors.length);
        assertEquals("com.khaydev.model.Goat", constructors[0].getName());
    }

    @Test
    @Order(8)
    void testShouldVerifyMethodInfo(){
        Object goat = new Goat();
        Class<?> goatClass = goat.getClass();
        Method[] methods = goatClass.getDeclaredMethods();
        List<String> methodNames = getMethodNames(methods);

        assertEquals(3, methodNames.size());
        assertTrue(List.of("eats", "getLocomotion", "getSound").containsAll(methodNames));
    }

    private List<String> getMethodNames(Method[] methods){
        return Arrays.stream(methods).map(Method::getName).toList();
    }
}
