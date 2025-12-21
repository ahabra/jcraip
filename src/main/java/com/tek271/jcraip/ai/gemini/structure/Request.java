package com.tek271.jcraip.ai.gemini.structure;

import com.tek271.jcraip.utils.JsonHelper;

import java.util.List;

public record Request(List<Content> content) {

  public String toJson(String indent) {
    return JsonHelper.toJson(this, indent);
  }

  public String toJson() {
    return toJson("");
  }


  public static Request fromJson(String json) {
    return JsonHelper.fromJson(Request.class, json);
  }

}
