package com.tek271.jcraip.ai.gemini;

public class GeminiCaller {
  private static final String URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent";


  private String getGeminiApiKey() {
    String key = System.getenv("GEMINI_API_KEY");
    if (key == null) {
      throw new IllegalStateException("GEMINI_API_KEY environment variable has not been set");
    }
    return key;
  }
}
