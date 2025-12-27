package com.tek271.jcraip.ai.base;

public interface AiCaller {
  AiAnswer call(AiQuestion question);
  String getName();
}
