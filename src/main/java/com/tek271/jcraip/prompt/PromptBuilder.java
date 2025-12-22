package com.tek271.jcraip.prompt;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PromptBuilder {

  /**
   * Build a prompt for a given method and its arguments
   */
  public List<String> buildPrompt(Method method, Object... args) {
    List<String> result = new ArrayList<>();
    String params = buildParameters(method.getParameters(), args);
    result.add(params);
    result.add(method.getDeclaredAnnotation(Prompt.class).value());
    result.add(buildResultPrompt());

    return result.stream().filter(s -> !s.isEmpty()).toList();
  }

  private String buildParameters(Parameter[] parameters, Object... args) {
    if (parameters.length == 0) {
      return "";
    }
    List<String> list = new ArrayList<>();
    for (int i = 0; i < parameters.length; i++) {
      list.add(buildParameter(parameters[i], args[i]));
    }
    return "Given the arguments " + String.join(" and ", list);
  }

  private String buildParameter(Parameter parameter, Object value) {
    String val = Objects.toString(value);
    if (parameter.isNamePresent()) {
      return parameter.getName() + "=" + val;
    }
    return val;
  }

  private String buildResultPrompt() {
    return "and return the result as result=";
  }

  public String buildPromptText(Method method, Object[] args) {
    return String.join("\n", buildPrompt(method, args));
  }


}
