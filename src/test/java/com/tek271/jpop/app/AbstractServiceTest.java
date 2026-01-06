package com.tek271.jpop.app;

import com.tek271.jpop.ai.bogus.BogusCaller;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AbstractServiceTest {
  BogusCaller aiCaller = new BogusCaller();
  AbstractService sut = AbstractService.createService(aiCaller, false);

  @Test
  void multiply_abstract_test() {
    aiCaller.ifQthenA("Given the arguments: 9 and 10, multiply them", "90");
    assertEquals(90, sut.multiply_abstract(9, 10));
  }

  @Test
  void multiply_real_test() {
    aiCaller.ifQthenA("Given the arguments: 9 and 10, multiply them", "90");
    assertEquals(90, sut.multiply_real(9, 10));
  }

}