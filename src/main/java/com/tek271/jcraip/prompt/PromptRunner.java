package com.tek271.jcraip.prompt;

import com.tek271.jcraip.ai.gemini.GeminiCaller;
import com.tek271.jcraip.ai.gemini.structure.Answer;

import java.lang.reflect.Method;

public class PromptRunner {
  GeminiCaller geminiCaller =  new GeminiCaller();

  public Object run(Method method, Object[] args) {
    PromptBuilder promptBuilder = new PromptBuilder();
    String prompt = promptBuilder.buildPromptText(method, args);

    Answer answer = geminiCaller.call(prompt);
    if (answer.httpStatus() != 200) {
      throw new RuntimeException(answer.toString());
    }
    String answerText = answer.getAnswerAfterPrefix("result=");

    // TODO coerce answerText to the method return type

    return answerText;
  }


}
