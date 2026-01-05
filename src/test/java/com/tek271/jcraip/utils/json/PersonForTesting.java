package com.tek271.jcraip.utils.json;

public record PersonForTesting(String name, int age) implements CanJson<PersonForTesting> {

  public static final PersonForTesting EMPTY = new PersonForTesting("", 0);
  public static final PersonForTesting ADA = new PersonForTesting("Ada", 10);
  public static final PersonForTesting SAM = new PersonForTesting("Sam", 42);
  public static final PersonForTesting SKY = new PersonForTesting("Sky", 14);

  public static final String SAM_JSON = SAM.toJson();

  public static final String ADA_SAM_SKY_JSON = String.format("[%s,%s,%s]",
    ADA.toJson(), SAM.toJson(), SKY.toJson());



}
