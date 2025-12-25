package com.tek271.jcraip.app;

import com.tek271.jcraip.AiObjectFactory;
import com.tek271.jcraip.prompt.Prompt;
import org.apache.commons.lang3.NotImplementedException;

public abstract class AbstractService {

  public static AbstractService createService() {
    return new AiObjectFactory().createProxy(AbstractService.class);
  }

  @Prompt("multiply them")
  public abstract int multiply_abstract(int a, int b);


  @Prompt("multiply them")
  public int multiply_real(int a, int b) {
    throw new NotImplementedException();
  }

}
