package com.tek271.jcraip.ai.gemini;

import com.tek271.jcraip.ai.gemini.structure.RequestPayload;
import com.tek271.jcraip.ai.gemini.structure.RespPayload;
import com.tek271.jcraip.ai.gemini.structure.Answer;
import com.tek271.jcraip.utils.net.HttpCaller;

import java.net.http.HttpResponse;

public class GeminiCaller {
  private static final String URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent";

  public Answer call(String prompt) {
    String apiKey = getGeminiApiKey();
    RequestPayload payload = new RequestPayload(prompt);
    HttpResponse<String> response = new HttpCaller()
      .url(URL)
      .methodPost()
      .contentTypeJson()
      .addHeader("x-goog-api-key", apiKey)
      .body(payload.toJson())
      .send();

    int httpStatus = response.statusCode();
    String body = response.body();
    if (httpStatus != 200) {
      return new Answer(httpStatus, body);
    }
    RespPayload respPayload = RespPayload.fromJson(body);
    String text = respPayload.getFirstAnswer();
    return new Answer(httpStatus, text);
  }


  private String getGeminiApiKey() {
    String key = System.getenv("GEMINI_API_KEY");
    if (key == null) {
      throw new IllegalStateException("GEMINI_API_KEY environment variable has not been set");
    }
    return key;
  }

  private RequestPayload createPayload(String prompt) {
    return new RequestPayload(prompt);
  }

}
