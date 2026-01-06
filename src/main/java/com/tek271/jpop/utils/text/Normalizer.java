package com.tek271.jpop.utils.text;

import com.google.common.base.Splitter;
import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Set;

import static java.util.stream.Collectors.joining;

public class Normalizer {
  private static final Set<String> STOP_WORDS = Set.of("them", "then", "the",
    "and", "as", "is", "are",
    "result", "return");

  private static final Set<String> SYMBOLS = Set.of("\\,", "\\n", "\\r", "\\t");
  private static final String SYMBOLS_REGEX = String.join("|", SYMBOLS);

  public static String normalize(String text) {
    text = removeSymbols(text);
    text = removeSpacesAroundEquals(text.toLowerCase());
    text = removeStopWords(text);
    text = removeLeftSideOfEqual(text);
    return StringUtils.deleteWhitespace(text);
  }

  static String removeSymbols(String text) {
    return RegExUtils.replaceAll(text, SYMBOLS_REGEX, " ");
  }

  static String removeSpacesAroundEquals(String text) {
    text = text.trim();
    boolean isEndsWithEquals = text.endsWith("=");
    text = Splitter.on('=').trimResults().omitEmptyStrings()
      .splitToStream(text)
      .collect(joining("="));
    return isEndsWithEquals? text + "=" : text;
  }

  static String removeStopWords(String text) {
    return Splitter.on(' ').trimResults().omitEmptyStrings()
      .splitToStream(text)
      .filter(w -> !STOP_WORDS.contains(w))
      .collect(joining(" "));
  }

  static String removeLeftSideOfEqual(String text) {
    return Splitter.on(' ').splitToStream(text)
      .map(w -> w.contains("=")? StringUtils.substringAfter(w, "=") : w)
      .collect(joining(" "));
  }

}
