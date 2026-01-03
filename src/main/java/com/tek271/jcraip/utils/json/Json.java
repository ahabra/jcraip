package com.tek271.jcraip.utils.json;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import java.lang.reflect.Type;

import static java.lang.String.format;

public class Json {
  private Class<?> baseType;
  private Class<?>[] parametrizedTypes;
  private final Moshi moshi;
  private String indent = "";

  public Json() {
    moshi = new Moshi.Builder().build();
  }

  public <T> Json baseType(Class<T> baseType) {
    this.baseType = baseType;
    return this;
  }

  public Json parametrizedTypes(Class<?>... parametrizedTypes) {
    this.parametrizedTypes = parametrizedTypes;
    return this;
  }

  private <T> JsonAdapter<T> moshiAdapter() {
    if (parametrizedTypes == null || parametrizedTypes.length == 0) {
      return moshi.adapter((Class<T>) baseType);
    }
    Type type = Types.newParameterizedType(baseType, parametrizedTypes);
    return moshi.adapter(type);
  }

  public Json indent(String indent) {
    this.indent = indent;
    return this;
  }

  @SuppressWarnings("unchecked")
  public <T> String toText(T obj) {
    if (obj == null) {
      return "null";
    }

    Class<T> objType = (Class<T>) obj.getClass();
    if (baseType == null) {
      this.baseType = objType;
    } else if (!baseType.isAssignableFrom(objType)) {
      String msg = format("Object of type %s must be of type %s", objType.getName(), baseType.getName());
      throw new IllegalArgumentException(msg);
    }
    return moshiAdapter().indent(indent).toJson(obj);
  }

  



}
