package com.tek271.jcraip.ai.gemini.structure;

import java.util.List;
import java.util.stream.Stream;

public record Content(String role, List<Part> parts) {

  public Content(List<Part> parts) {
    this("user", parts);
  }

  public Content(String... parts) {
    this(toPartList(parts));
  }


  private static List<Part> toPartList(String... parts) {
    return Stream.of(parts).map(Part::new).toList();
  }

}
