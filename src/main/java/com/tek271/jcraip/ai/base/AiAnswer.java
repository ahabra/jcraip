package com.tek271.jcraip.ai.base;

import org.apache.commons.lang3.StringUtils;

public record AiAnswer(int responseCode, String text) {

  public AiAnswer(String text) {
    this(200, text);
  }

  public String getAnswerAfterPrefix(String prefix) {
    return StringUtils.substringAfter(text, prefix);
  }

}
