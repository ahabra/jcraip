package com.tek271.jpop.app;

import com.tek271.jpop.prompt.Prompt;
import org.apache.commons.lang3.NotImplementedException;

public abstract class AbstractService {

  @Prompt("multiply them")
  public abstract int multiply_abstract(int a, int b);


  @Prompt("multiply them")
  public int multiply_real(int a, int b) {
    throw new NotImplementedException();
  }

}
