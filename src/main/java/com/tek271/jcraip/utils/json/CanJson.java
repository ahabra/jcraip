package com.tek271.jcraip.utils.json;

public interface CanJson<T> {

  default String toJson(String indent) {
    return Json.json().indent(indent).toText(this);
  }

  default String toJson() {
    return toJson("");
  }

  @SuppressWarnings("unchecked")
  default T fromJson(String json) {
    Class<T> type = (Class<T>) this.getClass();
    return Json.<T>json().baseType(type).parse(json);
  }

}
