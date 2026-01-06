package com.tek271.jpop.utils.reflect;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TypeCoercerTest {
  TypeCoercer sut = new TypeCoercer();

  @Test
  void cannotCoerceToNull() {
    assertThrows(IllegalArgumentException.class, () -> sut.coerce("", null));
  }

  @Test
  void coerceNull() {
    assertNull(sut.coerce(null, String.class));
  }

  @Test
  void coerceToString() {
    assertEquals("ab", sut.coerce("ab", String.class));
  }

  @Test
  void coerceToWholeNumber() {
    assertEquals((byte) 12, sut.coerce("12", byte.class));
    assertEquals((byte) 23, sut.coerce("23", Byte.class));
    assertEquals((byte) 0, sut.coerce("", Byte.class));

    assertEquals((short) 34, sut.coerce("34", short.class));
    assertEquals((short) 45, sut.coerce("45", Short.class));
    assertEquals((short) 0, sut.coerce("", short.class));

    assertEquals(56000, sut.coerce("56000", int.class));
    assertEquals(67000, sut.coerce("67000", Integer.class));
    assertEquals(0, sut.coerce("", Integer.class));

    assertEquals(78000L, sut.coerce("78000", long.class));
    assertEquals(89000L, sut.coerce("89000", Long.class));
    assertEquals(0L, sut.coerce("", long.class));
  }

  @Test
  void coerceToFraction() {
    assertEquals((float) 12.01, sut.coerce("12.01", float.class));
    assertEquals((float) 23.01, sut.coerce("23.01", Float.class));
    assertEquals((float) 0.0, sut.coerce("", float.class));

    assertEquals(34.01, sut.coerce("34.01", double.class));
    assertEquals(45.01, sut.coerce("45.01", Double.class));
    assertEquals(0.0, sut.coerce("", double.class));
  }

  @Test
  void coerceToBoolean() {
    assertEquals(true, sut.coerce("true", boolean.class));
    assertEquals(true, sut.coerce("true", Boolean.class));

    assertEquals(false, sut.coerce("false", boolean.class));
    assertEquals(false, sut.coerce("False", Boolean.class));

    assertEquals(false, sut.coerce("", Boolean.class));
  }

  @Test
  void coerceToChar() {
    assertEquals('a', sut.coerce("a", char.class));
    assertEquals('b', sut.coerce("b", Character.class));
  }


}