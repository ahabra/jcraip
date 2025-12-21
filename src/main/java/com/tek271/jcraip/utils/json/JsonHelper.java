package com.tek271.jcraip.utils.json;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;

public class JsonHelper {

  public static <T> JsonAdapter<T> moshiAdapter(Class<T> type) {
    Moshi moshi = new Moshi.Builder().build();
    return moshi.adapter(type);
  }

  public static <T> String toJson(T obj, String indent) {
    if (obj == null) {
      return "null";
    }

    @SuppressWarnings("unchecked")
    Class<T> t = (Class<T>) obj.getClass();
    return moshiAdapter(t).indent(indent).toJson(obj);
  }

  public static <T> String toJson(T obj) {
    return toJson(obj, "");
  }

  public static <T> T fromJson(Class<T> type, String json) {
    try {
      return moshiAdapter(type).fromJson(json);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}
