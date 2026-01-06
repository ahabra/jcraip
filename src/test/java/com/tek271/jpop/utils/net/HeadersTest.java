package com.tek271.jpop.utils.net;

import com.tek271.jpop.utils.structures.Pair;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HeadersTest {
  Headers sut =  new Headers();

  @Test
  void toList_hasItemsAsManyAsAdded() {
    sut.add("k1", "v1");
    sut.add("k2", "v2");
    sut.add("k2", "v3");

    List<Pair<String, String>> pairs = sut.toList();
    assertEquals(3, pairs.size());
    assertEquals(Pair.of("k1", "v1"), pairs.get(0));
    assertEquals(Pair.of("k2", "v2"), pairs.get(1));
    assertEquals(Pair.of("k2", "v3"), pairs.get(2));
  }

  @Test
  void get_returnsNullIfNotFound() {
    assertNull(sut.get("k1"));
    sut.add("k1", "v1");
    assertNull(sut.get("k2"));
  }

  @Test
  void get_ignoresCase() {
    sut.add("abc", "v1");
    assertEquals("v1", sut.get("abc").getFirst());
    assertEquals("v1", sut.get("ABC").getFirst());
    assertEquals("v1", sut.get("AbC").getFirst());
  }

  @Test
  void testGetFirst() {
    sut.add("k1", "v1");
    sut.add("k1", "v2");
    assertEquals("v1", sut.getFirst("k1"));
    assertNull(sut.getFirst("k2"));
  }

  @Test
  void contentType_addsHeader() {
    sut.contentTypeJson();
    List<Pair<String, String>> pairs = sut.toList();
    assertEquals(1, pairs.size());
    assertEquals(Pair.of("Content-Type", "application/json"), pairs.getFirst());
  }

}