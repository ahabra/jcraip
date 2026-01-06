package com.tek271.jpop.prompt;

import com.tek271.jpop.ai.base.AiAnswer;
import com.tek271.jpop.ai.base.AiCaller;
import com.tek271.jpop.ai.base.AiQuestion;
import com.tek271.jpop.utils.reflect.TypeCoercer;

import java.lang.reflect.Method;

public class PromptRunnerImpl implements PromptRunner {
  private final AiCaller aiCaller;
  private final TypeCoercer typeCoercer = new TypeCoercer();
  private boolean isLog;

  public PromptRunnerImpl(AiCaller aiCaller) {
    this.aiCaller = aiCaller;
  }

  @Override
  public Object run(Method method, Object[] args) {
    PromptBuilder promptBuilder = new PromptBuilder();
    AiQuestion aiQuestion = promptBuilder.buildAiQuestion(method, args);
    logQuestion(aiQuestion);

    AiAnswer answer = aiCaller.call(aiQuestion);
    if (answer.responseCode() != 200) {
      throw new RuntimeException(answer.toString());
    }
    String answerText = answer.getAnswerAfterPrefix("result=");

    logAnswer(answer);
    return typeCoercer.coerce(answerText, method.getReturnType());
  }

  @Override
  public PromptRunner logging(boolean isLog) {
    this.isLog = isLog;
    return this;
  }

  @Override
  public boolean isLogging() {
    return isLog;
  }

  private void log(String message) {
    if (isLog) {
      System.out.println(message);
    }
  }

  private void logQuestion(AiQuestion aiQuestion) {
    log(aiCaller.getName() + " Q: " + aiQuestion.text());
  }

  private void logAnswer(AiAnswer aiAnswer) {
    log("  A: " + aiAnswer.text());
  }



}
