package com.khaydev.model;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BirdTest {

    @Test
    @Order(1)
    void testShouldVerifyConstructorInfo(){
        Object bird = new Bird();
        Class<?> birdClass = bird.getClass();
        Constructor<?>[] constructors = birdClass.getConstructors();

        assertEquals(3, constructors.length);
    }

    @SuppressWarnings("java:S2699") //No assertions in test silencer
    @Test
    @Order(2)
    void testShouldRetrieveAvailableConstructors() throws ClassNotFoundException, NoSuchMethodException {
        //No need for assertion because NoSuchMethodException will be thrown automatically if a constructor with the parameter signature does not exist
        Class<?> birdClass = createBirdClass();

        Constructor<?> cons1 = birdClass.getConstructor();
        Constructor<?> cons2 = birdClass.getConstructor(String.class);
        Constructor<?> cons3 = birdClass.getConstructor(String.class, boolean.class);
    }

    @Test
    @Order(3)
    void testShouldRetrieveAvailableConstructorsAndInstantiateObjectsAtRuntime() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> birdClass = createBirdClass();

        Constructor<?> constructor1 = birdClass.getConstructor();
        Constructor<?> constructor2 = birdClass.getConstructor(String.class);
        Constructor<?> constructor3 = birdClass.getConstructor(String.class, boolean.class);

        Bird bird1 = (Bird) constructor1.newInstance();
        Bird bird2 = (Bird) constructor2.newInstance("Ticking Bird");
        Bird bird3 = (Bird) constructor3.newInstance("Dove", true);

        assertFalse(bird1.walks());
        assertTrue(bird3.walks());
        assertEquals("bird", bird1.getName());
        assertEquals("Ticking Bird", bird2.getName());
    }

    @Test
    @Order(4)
    void testShouldRetrievePublicFieldsInClassAndSuperClasses() throws ClassNotFoundException {
        Class<?> birdClass = createBirdClass();
        Field[] publicFields = birdClass.getFields();

        assertEquals(1, publicFields.length);
        assertEquals("CATEGORY", publicFields[0].getName());
    }

    @Test
    @Order(5)
    void testShouldRetrievePublicFieldSpecified() throws ClassNotFoundException, NoSuchFieldException {
        Class<?> birdClass = createBirdClass();
        Field publicField = birdClass.getField("CATEGORY");

        assertEquals("CATEGORY", publicField.getName());
    }

    @Test
    @Order(6)
    void testShouldVerifyDataTypeOfFields() throws ClassNotFoundException, NoSuchFieldException {
        Class<?> birdClass = createBirdClass();
        Field walksField = birdClass.getDeclaredField("walks");
        Class<?> walksType = walksField.getType();

        assertEquals("boolean", walksType.getSimpleName());
    }

    @Test
    @Order(7)
    void testShouldRetrieveFieldAndSetValue() throws ClassNotFoundException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> birdClass = createBirdClass();
        Bird bird = (Bird) birdClass.getConstructor().newInstance();
        Field walksField = birdClass.getDeclaredField("walks");
        walksField.setAccessible(true);

        assertFalse(walksField.getBoolean(bird));
        assertFalse(bird.walks());

        walksField.set(bird, true);

        assertTrue(walksField.getBoolean(bird));
        assertTrue(bird.walks());
    }

    @Test
    @Order(8)
    void testShouldInvokeRetrievedMethods() throws NoSuchMethodException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Class<?> birdClass = createBirdClass();
        Bird bird = (Bird) birdClass.getConstructor().newInstance();


        assertFalse(bird.walks());

        Method setWalksMethod = birdClass.getDeclaredMethod("setWalks", boolean.class);
        setWalksMethod.invoke(bird, true);

        assertTrue(bird.walks());
    }

    private Class<?> createBirdClass() throws ClassNotFoundException {
        return Class.forName("com.khaydev.model.Bird");
    }
}
