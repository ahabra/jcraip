package com.tek271.jcraip.ai.bogus;

import com.tek271.jcraip.ai.base.AiAnswer;
import com.tek271.jcraip.ai.base.AiCaller;
import com.tek271.jcraip.ai.base.AiQuestion;
import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * A bogus AI caller that can be trained to answer test questions
 */
public class BogusCaller implements AiCaller {
  private static final Set<String> STOP_WORDS_SET = Set.of("them", "then", "the", "and", "as", "is", "are", ",");
  private static final String STOP_WORDS = StringUtils.join(STOP_WORDS_SET, "|");

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
    return RegExUtils.removeAll(text, STOP_WORDS);
  }

}
