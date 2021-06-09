package org.sberstart.counter.model;

import java.math.BigInteger;

public class Counter {

  private final Integer id;
  private BigInteger value;

  public Counter(Integer id) {
    this.id = id;
    this.value = BigInteger.ZERO;
  }

  public Integer getId() {
    return id;
  }

  public BigInteger getValue() {
    return value;
  }

  public BigInteger increment() {
    value = value.add(BigInteger.ONE);
    return value;
  }
}
