package com.tek271.jpop.prompt;

import java.lang.annotation.*;

@Documented
@Target( ElementType.METHOD )
@Retention( RetentionPolicy.RUNTIME )
public @interface Prompt {
  String value();
}
