package com.tek271.jcraip.utils;

import java.util.Collection;

public class CollectionTools {

  public static <T> void print(Collection<T> col, boolean printLineCounter) {
    int counter = 0;
    for (T item : col) {
      String line = "";
      if (printLineCounter) {
        line = counter + ": ";
      }
      counter++;
      line += item;
      System.out.println(line);
    }
  }

}
