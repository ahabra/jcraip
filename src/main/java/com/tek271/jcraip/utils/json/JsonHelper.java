package com.tek271.jcraip.utils.json;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import java.io.IOException;
import java.lang.reflect.Type;

public class JsonHelper {

  public static Moshi moshi() {
    return new Moshi.Builder().build();
  }

  public static <T> JsonAdapter<T> moshiAdapter(Type type) {
    return moshi().adapter(type);
  }

  public static <T> JsonAdapter<T> moshiAdapter(Class<T> cls, Class<?>... param) {
    if (param.length == 0) {
      return moshi().adapter(cls);
    }
    Type type = Types.newParameterizedType(cls, param);
    return moshi().adapter(type);
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

  @SuppressWarnings("unchecked")
  public static <T> T fromJson(Class<T> type, String json) {
    try {
      return (T) moshiAdapter(type).fromJson(json);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}
