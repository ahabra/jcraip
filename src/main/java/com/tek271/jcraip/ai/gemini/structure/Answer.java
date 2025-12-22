package com.tek271.jcraip.ai.gemini.structure;

import org.apache.commons.lang3.StringUtils;

public record Answer(int httpStatus, String answer) {

  public String getAnswerAfterPrefix(String prefix) {
    return StringUtils.substringAfter(answer, prefix);
  }

}
