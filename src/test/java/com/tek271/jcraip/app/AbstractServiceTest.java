package com.tek271.jcraip.app;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AbstractServiceTest {
  AbstractService sut = AbstractService.createService();

  @Test
  void multiply_abstract_test() {
    assertEquals(90, sut.multiply_abstract(9, 10));
  }

  @Test
  void multiply_real_test() {
    assertEquals(90, sut.multiply_real(9, 10));
  }

}