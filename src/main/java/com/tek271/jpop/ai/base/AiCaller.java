package com.tek271.jpop.ai.base;

public interface AiCaller {
  AiAnswer call(AiQuestion question);
  String getName();
}
