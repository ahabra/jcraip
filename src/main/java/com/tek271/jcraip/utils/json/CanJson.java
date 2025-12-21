package com.tek271.jcraip.utils.json;

public interface CanJson<T> {

  default String toJson(String indent) {
    return JsonHelper.toJson(this, indent);
  }

  default String toJson() {
    return toJson("");
  }

  default T fromJson(String json) {
    @SuppressWarnings("unchecked")
    Class<T> type = (Class<T>) this.getClass();
    return JsonHelper.fromJson(type, json);
  }


}
