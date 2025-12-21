package com.tek271.jcraip.utils.structures;

public record Pair<K, V>(K key, V value) {

  public static <K, V> Pair<K, V> of(K k, V v) {
    return new Pair<>(k, v);
  }

}
