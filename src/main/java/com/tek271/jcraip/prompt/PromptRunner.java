package com.tek271.jcraip.prompt;

import com.tek271.jcraip.ai.gemini.GeminiCaller;
import com.tek271.jcraip.ai.gemini.structure.Answer;
import com.tek271.jcraip.utils.reflect.TypeCoercer;

import java.lang.reflect.Method;

public class PromptRunner {
  GeminiCaller geminiCaller =  new GeminiCaller();
  TypeCoercer typeCoercer = new TypeCoercer();

  public Object run(Method method, Object[] args) {
    PromptBuilder promptBuilder = new PromptBuilder();
    String prompt = promptBuilder.buildPromptText(method, args);
    System.out.println("Q: " +  prompt);

    Answer answer = geminiCaller.call(prompt);
    if (answer.httpStatus() != 200) {
      throw new RuntimeException(answer.toString());
    }
    String answerText = answer.getAnswerAfterPrefix("result=");

    System.out.println(" A: " + answerText);
    Object result = typeCoercer.coerce(answerText, method.getReturnType());

    return result;
  }


}
