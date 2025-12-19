package com.tek271.jcraip.prompt;

import java.lang.annotation.*;

@Documented
@Target( ElementType.METHOD )
@Retention( RetentionPolicy.RUNTIME )
public @interface Prompt {
  String value();
}
