package com.tek271.jcraip.ai.gemini.structure;

import com.tek271.jcraip.utils.JsonHelper;

import java.util.List;

public record RespPayload(List<RespCandidate> candidates) {

  public String getFirstAnswer() {
    return candidates.getFirst().content().parts().getFirst().text();
  }

  public String toJson(String indent) {
    return JsonHelper.toJson(this, indent);
  }

  public String toJson() {
    return toJson("");
  }

  public static RespPayload fromJson(String json) {
    return JsonHelper.fromJson(RespPayload.class, json);
  }

}
