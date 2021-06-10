package org.sberstart.counter;

public class NoCountersException extends RuntimeException {

  private static final String MESSAGE = "There are no counters here";

  public NoCountersException(String message) {
    super(message);
  }

  public NoCountersException() {
    super(MESSAGE);
  }
}
