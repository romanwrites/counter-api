package org.sberstart.counter;

public class NoSuchCounterIdException extends RuntimeException {

  private static final String MESSAGE_PREFIX = "There is no counter with id: ";

  public NoSuchCounterIdException(Integer id) {
    super(MESSAGE_PREFIX + id);
  }
}
