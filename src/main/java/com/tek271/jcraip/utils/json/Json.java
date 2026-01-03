package com.tek271.jcraip.utils.json;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import java.io.IOException;
import java.lang.reflect.Type;

import static java.lang.String.format;

public class Json<BT> {
  private Class<?> baseType;
  private Class<?>[] parametrizedTypes;
  private final Moshi moshi;
  private String indent = "";

  public Json() {
    moshi = new Moshi.Builder().build();
  }

  public static <T> Json<T> json() {
    return new Json<T>();
  }

  public Json<BT> baseType(Class<BT> baseType) {
    this.baseType = baseType;
    return this;
  }

  public Json<BT> parametrizedTypes(Class<?>... parametrizedTypes) {
    this.parametrizedTypes = parametrizedTypes;
    return this;
  }

  @SuppressWarnings("unchecked")
  private JsonAdapter<BT> moshiAdapter() {
    if (parametrizedTypes == null || parametrizedTypes.length == 0) {
      return moshi.adapter((Class<BT>) baseType);
    }
    Type type = Types.newParameterizedType(baseType, parametrizedTypes);
    return moshi.adapter(type);
  }

  public Json<BT> indent(String indent) {
    this.indent = indent;
    return this;
  }

  @SuppressWarnings("unchecked")
  public String toText(BT obj) {
    if (obj == null) {
      return "null";
    }

    Class<BT> objType = (Class<BT>) obj.getClass();
    if (baseType == null) {
      this.baseType = objType;
    } else if (!baseType.isAssignableFrom(objType)) {
      String msg = format("Object of type %s must be of type %s", objType.getName(), baseType.getName());
      throw new IllegalArgumentException(msg);
    }
    return moshiAdapter().indent(indent).toJson(obj);
  }

  public BT parse(String text) {
    try {
      return (BT) moshiAdapter().fromJson(text);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }



}
