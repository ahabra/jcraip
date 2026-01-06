package com.tek271.jpop.utils.reflect;

import java.util.Set;

public class TypeCoercer {

  private interface EmptyValues {
    Byte BYTE = 0;
    Float FLOAT = 0f;
    Boolean BOOLEAN = false;
    Character CHAR = 0;
  }

  private static final Set<Class<?>> COERCIBLE_TYPES = Set.of(
    String.class,
    boolean.class, Boolean.class,
    char.class, Character.class,
    byte.class, Byte.class,
    short.class, Short.class,
    int.class, Integer.class,
    long.class, Long.class,
    float.class, Float.class,
    double.class, Double.class
  );

  public boolean isCoercibleType(Class<?> type) {
    return COERCIBLE_TYPES.contains(type);
  }

  @SuppressWarnings("unchecked")
  public <T> T coerce(String value, Class<T> type) {
    if (type == null) {
      throw new IllegalArgumentException("type is null. Cannot coerce to null type");
    }

    if (value == null) {
      return null;
    }
    if (!isCoercibleType(type)) {
      return null;
    }

    if (String.class.equals(type)) {
      return (T) value;
    }

    T v = coercePrimitiveAndWrapperTypes(value, type);
    if (v != null) {
      return v;
    }

    return null;
  }

  @SuppressWarnings("unchecked")
  private <T> T coercePrimitiveAndWrapperTypes(String value, Class<T> type) {
    boolean isEmpty = value.isEmpty();
    if (boolean.class.equals(type) || Boolean.class.equals(type)) {
      return isEmpty? (T) EmptyValues.BOOLEAN : (T) Boolean.valueOf(value);
    }

    if (char.class.equals(type) || Character.class.equals(type)) {
      return isEmpty? (T) EmptyValues.CHAR : (T) Character.valueOf(value.charAt(0));
    }

    T v = coerceWholeNumber(value, type);
    if (v != null) {
      return v;
    }

    v = coerceFraction(value, type);
    if (v != null) {
      return v;
    }

    return null;
  }

  @SuppressWarnings("unchecked")
  private <T> T coerceWholeNumber(String value, Class<T> type) {
    boolean isEmpty = value.isEmpty();
    byte emptyValue = EmptyValues.BYTE;

    if (byte.class.equals(type) || Byte.class.equals(type)) {
      return isEmpty? (T) Byte.valueOf(emptyValue) : (T) Byte.valueOf(value);
    }
    if (short.class.equals(type) || Short.class.equals(type)) {
      return isEmpty? (T) Short.valueOf(emptyValue) : (T) Short.valueOf(value);
    }
    if (int.class.equals(type) || Integer.class.equals(type)) {
      return isEmpty? (T) Integer.valueOf(emptyValue) : (T) Integer.valueOf(value);
    }
    if (long.class.equals(type) || Long.class.equals(type)) {
      return isEmpty? (T) Long.valueOf(emptyValue) : (T) Long.valueOf(value);
    }
    return null;
  }

  @SuppressWarnings("unchecked")
  private <T> T coerceFraction(String value, Class<T> type) {
    boolean isEmpty = value.isEmpty();
    Float emptyValue = EmptyValues.FLOAT;

    if (float.class.equals(type) || Float.class.equals(type)) {
      return isEmpty? (T) emptyValue : (T) Float.valueOf(value);
    }

    if (double.class.equals(type) || Double.class.equals(type)) {
      return isEmpty? (T) Double.valueOf(emptyValue) : (T) Double.valueOf(value);
    }
    return null;
  }


}
