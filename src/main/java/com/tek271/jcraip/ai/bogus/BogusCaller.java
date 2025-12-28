package com.tek271.jcraip.ai.bogus;

import com.tek271.jcraip.ai.base.AiAnswer;
import com.tek271.jcraip.ai.base.AiCaller;
import com.tek271.jcraip.ai.base.AiQuestion;

import java.util.HashMap;
import java.util.Map;

import static com.tek271.jcraip.utils.text.Normalizer.normalize;

/**
 * A bogus AI caller that can be trained to answer test questions
 */
public class BogusCaller implements AiCaller {
  private final Map<String, AiAnswer> db =  new HashMap<>();

  @Override
  public AiAnswer call(AiQuestion question) {
    String text = normalize(question.text());
    System.out.println("Q text: " + text);
    return db.get(text);
  }

  @Override
  public String getName() {
    return "BogusCaller";
  }

  public void ifQthenA(String question, String answer) {
    question = normalize(question + " and return the result as result=");
    AiAnswer aiAnswer = new AiAnswer("result=" + answer);
    db.put(question, aiAnswer);
  }

}
