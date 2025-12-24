package com.tek271.jcraip.utils.reflect;

import org.mvel2.MVEL;

public class Evaluator {

  @SuppressWarnings("unchecked")
  public <T> T eval(String value) {
    return (T) MVEL.eval(value);
  }

}
