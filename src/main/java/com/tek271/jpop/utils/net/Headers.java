package com.tek271.jpop.utils.net;

import com.tek271.jpop.utils.structures.Pair;

import java.util.*;

public class Headers {
  static class Header {
    final String givenName;
    final List<String> values;

    Header(String givenName) {
      this.givenName = givenName;
      this.values = new ArrayList<>();
    }

  }

  private final Map<String, Header> content = new LinkedHashMap<>();

  public void add(String name, String value) {
    String upName = name.toUpperCase();
    Header header = content.computeIfAbsent(upName, k -> new Header(name));
    header.values.add(value);
  }

  public List<String> get(String name) {
    Header header = content.get(name.toUpperCase());
    if (header == null) {
      return null;
    }
    return header.values;
  }

  public String getFirst(String name) {
    List<String> values = get(name);
    if (values == null || values.isEmpty()) {
      return null;
    }
    return values.getFirst();
  }

  public List<Pair<String, String>> toList() {
    List<Pair<String, String>> result = new ArrayList<>();
    for (Header header : content.values()) {
      for (String value : header.values) {
        result.add(Pair.of(header.givenName, value));
      }
    }
    return result;
  }

  public void contentType(String value) {
    add("Content-Type", value);
  }

  public void contentTypeJson() {
    contentType("application/json");
  }




}
