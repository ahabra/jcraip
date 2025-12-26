package com.tek271.jcraip.ai.bogus;

import com.tek271.jcraip.ai.base.AiAnswer;
import com.tek271.jcraip.ai.base.AiCaller;
import com.tek271.jcraip.ai.base.AiQuestion;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Strings;

import java.util.HashMap;
import java.util.Map;

public class BogusCaller implements AiCaller {
  private final Map<String, AiAnswer> db =  new HashMap<>();

  @Override
  public AiAnswer call(AiQuestion question) {
    String text = normalize(question.text());
    return db.get(text);
  }

  public void ifQthenA(String question, String answer) {
    question = normalize(question + "and return the result as result=");
    AiAnswer aiAnswer = new AiAnswer("result=" + answer);
    db.put(question, aiAnswer);
  }

  private static String normalize(String text) {
    text = StringUtils.deleteWhitespace(text);
    text = text.toLowerCase();
    text = Strings.CI.remove(text, "the");
    text = Strings.CI.remove(text, "and");
    text = Strings.CI.remove(text, "as");
    text = Strings.CI.remove(text, "is");
    text = Strings.CI.remove(text, "are");
    return text;
  }

}
