package com.tek271.jcraip.ai.gemini;

import com.tek271.jcraip.ai.base.AiAnswer;
import com.tek271.jcraip.ai.base.AiCaller;
import com.tek271.jcraip.ai.base.AiQuestion;
import com.tek271.jcraip.ai.gemini.structure.RequestPayload;
import com.tek271.jcraip.ai.gemini.structure.RespPayload;
import com.tek271.jcraip.utils.net.HttpCaller;

import java.net.http.HttpResponse;

public class GeminiCaller implements AiCaller {
//  private static final String URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent";
  private static final String URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-3-pro-preview:generateContent";
  private static final String API_KEY_NAME = "GEMINI_API_KEY";

  /**
   * Call gemini using the env. variable GEMINI_API_KEY as the api key
   * @param aiQuestion the question to ask
   */
  @Override
  public AiAnswer call(AiQuestion aiQuestion) {
    String apiKey = getGeminiApiKey(API_KEY_NAME);
    RequestPayload payload = new RequestPayload(aiQuestion.text());
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
      return new AiAnswer(httpStatus, body);
    }
    RespPayload respPayload = RespPayload.EMPTY.fromJson(body);
    return new AiAnswer(httpStatus, respPayload.getFirstAnswer());
  }

  @Override
  public String getName() {
    return "GeminiCaller";
  }


  /**
   * Read the value of the given env. variable name
   * @param envVarName name of env. variable
   * @return The value of the env variable
   * @throws IllegalStateException if not found
   */
  static String getGeminiApiKey(String envVarName) {
    String key = System.getenv(envVarName);
    if (key == null) {
      throw new IllegalStateException(envVarName + " environment variable must be set to your Google API key");
    }
    return key;
  }


}
