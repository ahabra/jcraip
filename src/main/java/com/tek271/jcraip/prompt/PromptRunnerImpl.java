package com.tek271.jcraip.prompt;

import com.tek271.jcraip.ai.base.AiAnswer;
import com.tek271.jcraip.ai.base.AiQuestion;
import com.tek271.jcraip.ai.gemini.GeminiCaller;
import com.tek271.jcraip.utils.reflect.TypeCoercer;

import java.lang.reflect.Method;

public class PromptRunnerImpl implements PromptRunner {
  GeminiCaller geminiCaller =  new GeminiCaller();
  TypeCoercer typeCoercer = new TypeCoercer();

  @Override
  public Object run(Method method, Object[] args) {
    PromptBuilder promptBuilder = new PromptBuilder();
    AiQuestion aiQuestion = promptBuilder.buildAiQuestion(method, args);
    System.out.println("Q: " +  aiQuestion.text());

    AiAnswer answer = geminiCaller.call(aiQuestion);
    if (answer.responseCode() != 200) {
      throw new RuntimeException(answer.toString());
    }
    String answerText = answer.getAnswerAfterPrefix("result=");

    System.out.println(" A: " + answerText);
    return typeCoercer.coerce(answerText, method.getReturnType());
  }


}
