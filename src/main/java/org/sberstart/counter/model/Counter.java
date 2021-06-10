package org.sberstart.counter.model;

import java.math.BigInteger;
import java.util.Objects;

public class Counter {

  private final Integer id;
  private BigInteger value;

  public Counter(Integer id) {
    this.id = id;
    this.value = BigInteger.ZERO;
  }

  public Counter(Integer id, BigInteger value) {
    this.id = id;
    this.value = value;
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

  public void setValue(BigInteger value) {
    this.value = value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Counter counter = (Counter) o;
    return id.equals(counter.id) && value.equals(counter.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, value);
  }

  @Override
  public String toString() {
    return "Counter{" +
        "id=" + id +
        ", value=" + value +
        '}';
  }
}
