package com.tek271.jcraip.utils.text;

import org.junit.jupiter.api.Test;

import static com.tek271.jcraip.utils.text.Normalizer.*;
import static org.junit.jupiter.api.Assertions.*;

class NormalizerTest {

  @Test
  void removeSymbols_test() {
    assertEquals("", removeSymbols(""));
    assertEquals(" ", removeSymbols(","));
    assertEquals("  ", removeSymbols(",\n"));
    assertEquals("    ", removeSymbols(",\n\r\t"));
    assertEquals("     ", removeSymbols(",\n\r\t "));
  }

  @Test
  void removeSpacesAroundEquals_test() {
    assertEquals("", removeSpacesAroundEquals(""));
    assertEquals("", removeSpacesAroundEquals(" "));
    assertEquals("=", removeSpacesAroundEquals(" = "));
    assertEquals("a=1", removeSpacesAroundEquals(" a = 1"));
    assertEquals("a=1 b=2", removeSpacesAroundEquals(" a = 1 b  = 2"));
    assertEquals("a=", removeSpacesAroundEquals("a="));
    assertEquals("a=", removeSpacesAroundEquals("a = "));
  }

  @Test
  void removeStopWords_test() {
    assertEquals("", removeStopWords(""));
    assertEquals("", removeStopWords("the"));
    assertEquals("", removeStopWords(" the "));
    assertEquals("", removeStopWords("\nthe"));
    assertEquals("big", removeStopWords(" the big them "));
  }

  @Test
  void removeLeftSideOfEqual_test() {
    assertEquals("", removeLeftSideOfEqual(""));
    assertEquals("3", removeLeftSideOfEqual("a=3"));
    assertEquals("3 4", removeLeftSideOfEqual("a=3 b=4"));
  }

  @Test
  void normalize_test() {
    String text = "Given the arguments a=1 and b=4 and c=10 and d=20\nSum them all\nand return the result as result=";
    assertEquals("givenarguments141020sumall", normalize(text));
  }
}