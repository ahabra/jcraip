package com.tek271.jpop.prompt;

import com.tek271.jpop.ai.base.AiQuestion;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PromptBuilder {

  /**
   * Build a prompt for a given method and its arguments
   */
  public List<String> buildPrompt(Method method, Object... args) {
    List<String> params = buildParameters(method.getParameters(), args);
    List<String> result = new ArrayList<>(params);
    result.add(method.getDeclaredAnnotation(Prompt.class).value());
    result.add(buildResultPrompt(method));

    return result.stream().filter(s -> !s.isEmpty()).toList();
  }

  private List<String> buildParameters(Parameter[] parameters, Object... args) {
    if (parameters.length == 0) {
      return Collections.emptyList();
    }
    List<String> list = new ArrayList<>();
    list.add("Given the arguments:");
    for (int i = 0; i < parameters.length; i++) {
      String separator = i == 0? "" : "and ";
      list.add(buildParameter(parameters[i], args[i], separator));
    }
    return list;
  }

  private String buildParameter(Parameter parameter, Object value, String separator) {
    StringBuilder sb = new StringBuilder();
    sb.append(separator);
    if (parameter.isAnnotationPresent(IsJson.class) ) {
      sb.append("as JSON ");
    }
    if (parameter.isNamePresent()) {
      sb.append(parameter.getName()).append("=");
    }
    sb.append(value);
    return sb.toString();
  }

  private String buildResultPrompt(Method method) {
    if (method.isAnnotationPresent(IsJson.class)) {
      return "and return JSON result as result=";
    }
    return "and return the result as result=";
  }

  public AiQuestion buildAiQuestion(Method method, Object[] args) {
    String text = String.join("\n", buildPrompt(method, args));
    return new AiQuestion(text);
  }


}
