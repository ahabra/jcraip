package com.tek271.jcraip.ai.gemini.structure;

import com.tek271.jcraip.utils.json.CanJson;

import java.util.Collections;
import java.util.List;

public record RespPayload(List<RespCandidate> candidates) implements CanJson<RespPayload> {
  public static final RespPayload EMPTY = new RespPayload(Collections.emptyList());

  public String getFirstAnswer() {
    return candidates.getFirst().content().parts().getFirst().text();
  }

}
